package app.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private static final String HOST = "127.0.0.1";
    private static final Integer PORT = 12345;

    public static void main(String[] args)
            throws UnknownHostException, IOException {

        final Socket client = new Socket(HOST, PORT);
        System.out.println("Client connected to the server");

        final Scanner keyboard = new Scanner(System.in);
        PrintStream outStream = new PrintStream(client.getOutputStream());

        while (keyboard.hasNextLine()) {
            outStream.println(keyboard.nextLine());
        }

        outStream.close();
        keyboard.close();
    }
}
