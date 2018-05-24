package app.socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.Scanner;

public class AppClientSocket {
    public static Optional<Socket> tryConnection(String host, Integer port){
        try {
            final Socket client = new Socket(host, port);
            return Optional.of(client);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static void main(String[] args)
            throws UnknownHostException, IOException {


//        final Scanner keyboard = new Scanner(System.in);
//        PrintStream outStream = new PrintStream(client.getOutputStream());
//
//        while (keyboard.hasNextLine()) {
//            outStream.println(keyboard.nextLine());
//        }
//
//        outStream.close();
//        keyboard.close();
    }
}
