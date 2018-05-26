package app;

import app.gui.Tuple;
import app.socket.ServerResponse;
import app.socket.UserAction;

import java.io.*;
import java.net.Socket;
import java.util.Optional;

public class SocketHelper {

    public static Optional<Socket> tryConnection(String host, Integer port) {
        try {
            final Socket client = new Socket(host, port);
            return Optional.of(client);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static String getMessage(Socket socket) throws IOException {
        final InputStream is = socket.getInputStream();
        final InputStreamReader isr = new InputStreamReader(is);
        final BufferedReader br = new BufferedReader(isr);
        final String received = br.readLine();

        System.out.println("Message received : " + received);

        return received;
    }

    public static void sendMessage(Socket socket, String sendMessage) throws IOException {
        final OutputStream os = socket.getOutputStream();
        final OutputStreamWriter osw = new OutputStreamWriter(os);
        final BufferedWriter bw = new BufferedWriter(osw);
        bw.write(sendMessage + "\n");
        bw.flush();

        System.out.println("Message sent: " + sendMessage);
    }

    public static <T> Optional<T> getObjectMessage(Socket socket, Class<T> clazz) throws IOException {
        final InputStream is = socket.getInputStream();
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        try {
            final T received = clazz.cast(inStream.readObject());

            System.out.println("Object received : " + received);

            return Optional.of(received);
        } catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    private static void sendObjectMessage(Socket socket, Serializable message) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(message);
        System.out.println("Object sent: " + message);
    }


    public static void sendObjectMessage(Socket socket, UserAction action, Serializable message) throws IOException {
        sendObjectMessage(
                socket,
                Tuple.from(action, message)
        );
    }

    public static void sendResponse(Socket socket, ServerResponse response) throws IOException {
        sendObjectMessage(
                socket,
                response
        );
    }
}
