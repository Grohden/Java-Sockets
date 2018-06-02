package app.socket.comunication.server.responses;

import app.socket.comunication.server.ServerResponse;

public class SimpleError extends ServerResponse<String> {
    public SimpleError(String message) {
        super(null);
        setMessage(message);
    }

    @Override
    public boolean isError() {
        return true;
    }
}
