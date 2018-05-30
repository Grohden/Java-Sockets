package app.comunication.server.responses;

import app.museum.User;
import app.comunication.server.ServerResponse;

import java.util.Collection;

public class AllUsers extends ServerResponse<Collection<User>> {

    public AllUsers(Collection<User> result) {
        super(result);
    }

    @Override
    public boolean isError() {
        return false;
    }
}
