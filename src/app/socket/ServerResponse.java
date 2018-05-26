package app.socket;

import java.io.Serializable;

public enum ServerResponse implements Serializable {
    SUCCESS(false, "Operation success"),

    EMAIL_ALREADY_IN_USE(true, "This email is already being used, try another."),
    OPERATION_NOT_SUPPORTED(true, "Operation not supported");

    private final String message;
    private final Boolean isError;

    ServerResponse(Boolean isError, String message) {
        this.message = message;
        this.isError = isError;
    }


    public String getMessage() {
        return message;
    }

    public Boolean isError() {
        return this.isError;
    }
}
