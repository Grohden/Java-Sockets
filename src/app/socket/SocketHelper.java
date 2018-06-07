package app.socket;

import app.socket.comunication.client.ClientOption;
import app.utils.Tuple;

import java.io.*;
import java.net.Socket;
import java.util.Optional;

public class SocketHelper {

    public static Optional<Socket> tryConnection(Tuple<String, Integer> socketInfo) {
        try {
            final Socket client = new Socket(socketInfo.getLeft(), socketInfo.getRight());
            return Optional.of(client);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static void sendMessage(Socket socket, ClientOption sendMessage) throws IOException {
        final OutputStream os = socket.getOutputStream();
        final OutputStreamWriter osw = new OutputStreamWriter(os);
        final BufferedWriter bw = new BufferedWriter(osw);
        bw.write(sendMessage + "\n");
        bw.flush();

        System.out.println("Message sent: " + sendMessage);
        System.out.println();
    }

    public static <T> Optional<T> getObjectMessage(Socket socket, Class<T> clazz) {
        try {
            final InputStream is = socket.getInputStream();
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            final T received = clazz.cast(inStream.readObject());

            System.out.println("Object received : " + received);
            System.out.println();

            return Optional.of(received);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static void sendObjectMessage(Socket socket, Serializable message) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(message);

        System.out.println("Object sent: " + message);
        System.out.println();
    }
}
