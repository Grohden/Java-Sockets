package app;

import app.comunication.server.ServerResponse;
import app.comunication.server.responses.AllUsers;
import app.comunication.server.responses.SimpleError;
import app.comunication.server.responses.SimpleSuccess;
import app.utils.Tuple;
import app.museum.User;
import app.museum.UserRegistry;
import app.socket.ServerSocketHelper;
import app.socket.SocketHelper;
import app.socket.UserAction;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    private static final Integer DEFAULT_PORT = 0;
    private static final Integer MAX_RESPONSE_TRY = 3;
    private static final String MAX_RESPONSE_TRY_ERROR = "Error sending response to client";
    private static final String WAITING_CONNECTION = "Waiting for client, connect at ip %s, port %d";
    private static final String CONNECTION_SUCCESS = "Client connected successfully, waiting for message.";
    private static final String SOCKET_ERROR = "Socket error, exiting.";
    private static final String EMAIL_ALREADY_IN_USE = "This email is already being used, try another.";
    private static final String OPERATION_NOT_SUPPORTED = "Operation not supported";

    private static ServerResponse doRegister(User newUser) {
        final boolean success = UserRegistry.addUser(newUser);

        return success
                ? new SimpleSuccess("Success")
                : new SimpleError(EMAIL_ALREADY_IN_USE);
    }

    private static ServerResponse processAction(Tuple tuple) {
        UserAction ac = (UserAction) tuple.getLeft();

        switch (ac) {
            case REGISTER:
                return doRegister((User) tuple.getRight());
            case LIST_USERS:
                return doSendAllUsers();
            default:
                return new SimpleError(OPERATION_NOT_SUPPORTED);
        }
    }

    private static AllUsers doSendAllUsers() {
        return new AllUsers(UserRegistry.getAll());
    }

    private static void sendResponse(Socket server, ServerResponse response, Integer tryCount) {
        if (tryCount >= MAX_RESPONSE_TRY) {
            System.out.println(MAX_RESPONSE_TRY_ERROR);
        }

        try {
            ServerSocketHelper.sendResponse(server, response);
        } catch (IOException e) {
            sendResponse(server, response, tryCount + 1);
        }
    }

    private static void sendResponse(Socket server, ServerResponse response) {
        sendResponse(server, response, 0);
    }


    public static void boot() {
        try {
            final ServerSocket server = new ServerSocket(DEFAULT_PORT);
            final InetAddress localHost = InetAddress.getLocalHost();

            while(true){

                System.out.println(String.format(
                        WAITING_CONNECTION,
                        localHost.getHostAddress(),
                        server.getLocalPort()
                ));

                final Socket client = server.accept();
                System.out.println(CONNECTION_SUCCESS);

                SocketHelper
                        .getObjectMessage(client, Tuple.class)
                        .ifPresent(t ->
                                sendResponse(client, processAction(t))
                        );

                client.close();
            }

        } catch (IOException e) {
            System.out.println(SOCKET_ERROR);
        }

    }
}
