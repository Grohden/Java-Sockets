package app.socket.comunication.server;

import java.io.Serializable;
import java.util.Optional;

public abstract class ServerResponse<T> implements Serializable {
    private final T result;
    private String  message;

    protected ServerResponse(T result){
        this.result = result;
    }

    public Optional<String> getMessage(){
        return Optional.ofNullable(message);
    }

    public void setMessage(String message){
        this.message = message;
    };

    /**
     * Returns the result of the response
     */
    public T getReturn(){
        return this.result;
    }

    /**
     * If the response is an error or not
     */
    public abstract boolean isError();

    @Override
    public String toString() {
        return getReturn() + getMessage().map(m -> ":" + m).orElse("");
    }
}
