package app.comunication.server.responses;

import app.comunication.server.ServerResponse;

public class SimpleSuccess extends ServerResponse<String> {

    public SimpleSuccess(String result) {
        super(result);
    }

    public boolean isError() {
        return false;
    }
}
