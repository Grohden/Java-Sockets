package app.socket;

import app.socket.comunication.server.ServerResponse;

import java.io.IOException;
import java.net.Socket;

public class ServerSocketHelper {

    public static void sendResponse(Socket socket, ServerResponse response) throws IOException {
        SocketHelper.sendObjectMessage(
                socket,
                response
        );
    }

}
