package app.socket.comunication.server.responses;

import app.socket.comunication.server.ServerResponse;

import java.util.Collection;

public class CollectionResponse<T> extends ServerResponse<Collection<T>> {

    public CollectionResponse(Collection<T> result) {
        super(result);
    }

    @Override
    public boolean isError() {
        return false;
    }
}
