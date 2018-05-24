package app.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final Integer PORT = 0;

    public static void main(String[] args) throws IOException {
        final ServerSocket server = new ServerSocket(PORT);
        System.out.println("Waiting for client at port " + server.getLocalPort());
        final Socket client = server.accept();
        forwardToAnotherSocker(client);
    }

    private static void forwardToAnotherSocker(Socket client) throws IOException {
        System.out.println("Client connected " + client.getInetAddress().getHostAddress());

        final InputStream is = client.getInputStream();
        final InputStreamReader isr = new InputStreamReader(is);
        final BufferedReader br = new BufferedReader(isr);
        final String clientMessage = br.readLine();

        final ServerSocket clientServer = new ServerSocket(0);

    }

}
