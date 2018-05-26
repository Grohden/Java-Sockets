package app;

import app.gui.Menu;
import app.gui.Tuple;
import app.museum.User;
import app.socket.UserAction;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

public class ClientApp {
    private static final String CONNECTION_SUCCESS = "Connection succeeded";
    private static final String CONNECTION_ERROR = "Socket connection error, try again";

    private static final Menu<Consumer<Socket>> clientMenu = new Menu<>(
            "Client menu, choose what to do",
            Arrays.asList(
                    Tuple.from("Register", ClientApp::registerUser),
                    Tuple.from("Remove registry", s -> {
                    }),
                    Tuple.from("View all users", ClientApp::printAllUsers),
                    Tuple.from("See painting infos", s -> {
                    })
            )
    );

    /**
     * Prompts the user for an IP and a port number and try to connect
     * if it cant connect, try again until it works.
     */
    private static Socket getConnection() {
        String ip = Menu.prompt("Inform the server IP");

        Integer portNumber = Menu.promptInt(
                "Inform the port number",
                0,
                Integer.MAX_VALUE
        );

        Optional<Socket> clientSocket = SocketHelper.tryConnection(ip, portNumber);
        if (clientSocket.isPresent()) {
            System.out.println(CONNECTION_SUCCESS);
            return clientSocket.get();
        } else {
            System.out.println(CONNECTION_ERROR);
            return getConnection();
        }

    }

    private static void printAllUsers(Socket server) {


    }

    private static void registerUser(Socket server) {
        final User user = User.promptUser();

        try {
            SocketHelper.sendObjectMessage(server, UserAction.REGISTER, user);
            System.out.println("Success!");
        } catch (IOException e) {
            System.out.println("Error sending user");
        }

        boot();
    }

    /**
     * Boots the client app, asks for connection properties and
     * try to connect
     */
    public static void boot() {
        final Socket server = getConnection();

        clientMenu
                .spawnMenuWithExit("Exit")
                .ifPresent(socketConsumer -> socketConsumer.accept(server));
    }
}
