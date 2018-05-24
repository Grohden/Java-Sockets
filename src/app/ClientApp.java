package app;

import app.gui.Menu;
import app.gui.Tuple;
import app.socket.AppClientSocket;
import org.omg.PortableInterceptor.INACTIVE;

import java.net.Socket;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class ClientApp {
    private static final String CONNECTION_SUCCESS = "Connection succeeded";
    private static final String CONNECTION_ERROR = "Socket connection error, try again";

    private static final Menu<Integer> clientMenu = new Menu<>(
            "Client menu, choose what to do",
            Arrays.asList(
                    Tuple.from("Register email", 1),
                    Tuple.from("Remove email registry", 2),
                    Tuple.from("See painting infos", 3)
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

        Optional<Socket> clientSocket = AppClientSocket.tryConnection(ip, portNumber);
        if (clientSocket.isPresent()) {
            System.out.println(CONNECTION_SUCCESS);
            return clientSocket.get();
        } else {
            System.out.println(CONNECTION_ERROR);
            return getConnection();
        }

    }

    /**
     * Boots the client app, asks for connection properties and
     * try to connect
     */
    public static void boot() {
        getConnection();
    }
}
