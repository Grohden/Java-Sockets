package app.socket;

import java.io.Serializable;

public enum UserAction implements Serializable {
    HELLO,
    LIST_USERS,
    REGISTER;
}
