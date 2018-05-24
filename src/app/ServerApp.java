package app;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    private static final Integer DEFAULT_PORT = 0;
    private static final String WAITING_CONNECTION = "Waiting for client, connect at ip %s, port %d";

    public static void boot() {
        try {
            final ServerSocket server = new ServerSocket(DEFAULT_PORT);
            final InetAddress localHost = InetAddress.getLocalHost();

            System.out.println(String.format(
                    WAITING_CONNECTION,
                    localHost.getHostAddress(),
                    server.getLocalPort()
            ));

            final Socket client = server.accept();
            System.out.println("Client connected successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
