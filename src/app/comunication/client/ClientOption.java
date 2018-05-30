package app.comunication.client;

import java.util.Optional;

public interface ClientOption<T> {
    Optional<T> doProcess();
}
