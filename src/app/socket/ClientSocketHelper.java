package app.socket;

import app.socket.comunication.client.ClientOption;
import app.socket.comunication.server.ServerResponse;
import app.utils.Tuple;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.Optional;

public class ClientSocketHelper {

    private static void sendServerAction(Socket socket, ClientOption action, Serializable message) throws IOException {
        SocketHelper.sendObjectMessage(
                socket,
                Tuple.from(action, message)
        );
    }

    public static Optional<ServerResponse> sendMessage(Socket server, ClientOption action) {
        return sendMessage(server, action,null);
    }

    public static Optional<ServerResponse> sendMessage(Socket server, ClientOption action, Serializable object) {
        try {
            sendServerAction(server, action, object);
        } catch (IOException ignored) {}

        return SocketHelper.getObjectMessage(server, ServerResponse.class);
    }

}
