package app;

import app.console.Menu;
import app.museum.entities.User;
import app.socket.ClientSocketHelper;
import app.socket.SocketHelper;
import app.socket.comunication.client.ClientOption;
import app.socket.comunication.server.ServerResponse;
import app.socket.comunication.server.responses.AllUsers;
import app.utils.Tuple;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Optional;

public class ClientApp {
    private static final String CONNECTION_SUCCESS = "Connection succeeded";
    private static final String CONNECTION_ERROR = "Socket connection error, try again";
    private static Tuple<String, Integer> cachedSocketInformation;

    private static final Menu<Runnable> clientMenu = new Menu<>(
            "Client menu, choose what to do",
            Arrays.asList(
                    Tuple.from("Register", ClientApp::registerUser),
                    Tuple.from("Remove registry", () -> {
                    }),
                    Tuple.from("View all users", ClientApp::requestAllUsers),
                    Tuple.from("See painting infos", () -> {
                    })
            )
    );

    private static Tuple<String, Integer> promptSocketConnection() {
        String ip = Menu.prompt("Inform the server IP");

        Integer portNumber = Menu.promptInt(
                "Inform the port number",
                0,
                Integer.MAX_VALUE
        );

        return Tuple.from(ip, portNumber);
    }

    /**
     * Prompts the user for an IP and a port number and try to connect
     * if it cant connect, try again until it works.
     */
    private static Socket getAndCacheInitialConnection() {
        final Socket connection = getConnection();

        Optional<ServerResponse> response = ClientSocketHelper.sendMessage(connection, ClientOption.HELLO);

        try {
            connection.close();
        } catch (IOException ignored) {
        }

        System.out.println(CONNECTION_SUCCESS);
        return connection;
    }

    /**
     * Prompts the user for an IP and a port number and try to connect
     * if it cant connect, try again until it works.
     */
    private static Socket getConnection() {

        final Tuple<String, Integer> socketInformation;

        if (cachedSocketInformation == null) {
            cachedSocketInformation = promptSocketConnection();
        }

        Optional<Socket> clientSocket = SocketHelper.tryConnection(cachedSocketInformation);

        return clientSocket.orElseGet(ClientApp::getConnection);
    }

    private static void requestAllUsers() {
        Socket server = getConnection();
        Optional<ServerResponse> response = ClientSocketHelper.sendMessage(server, ClientOption.LIST_USERS, null);

        Boolean failed = response
                .map(ServerResponse::isError)
                .orElse(true);

        if (failed) {
            //FIXME: compiler bug here, do not remove the cast!
            final String errorMessage = (String) response
                    .flatMap(ServerResponse::getMessage)
                    .orElse("Error requesting users");

            System.out.println(errorMessage);
        } else {
            final AllUsers dataResponse = (AllUsers) (response.get());
            dataResponse
                    .getReturn()
                    .forEach(System.out::println);

            System.out.println("Success!");
        }

        closeSocket(server);

    }

    private static void closeSocket(Socket server) {
        try {
            server.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Tries to register a user in the server socket
     */
    private static void registerUser() {
        final User user = User.promptUser();

        Socket server = getConnection();
        Optional<ServerResponse> response = ClientSocketHelper.sendMessage(server, ClientOption.REGISTER, user);

        Boolean failed = response
                .map(ServerResponse::isError)
                .orElse(true);

        if (failed) {
            //FIXME: compiler bug here, do not remove the cast!
            final String errorMessage = (String) response
                    .flatMap(ServerResponse::getMessage)
                    .orElse("Server error sending user");

            System.out.println(errorMessage);
        } else {
            System.out.println("Success!");
        }

        closeSocket(server);
    }

    /**
     * Boots the client app, asks for connection properties and
     * try to connect
     */
    public static void boot() {
        Optional<Runnable> chosenOption;
        getAndCacheInitialConnection();

        do {
            chosenOption = clientMenu.spawnMenuWithExit("Exit");

            chosenOption.ifPresent(Runnable::run);

        } while (chosenOption.isPresent());


    }
}
