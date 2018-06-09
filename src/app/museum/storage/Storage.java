package app.museum.storage;

import app.museum.interfaces.SocketSendable;
import app.socket.comunication.server.responses.CollectionResponse;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class Storage<T> implements SocketSendable<T> {
    private final Collection<T> collection = new HashSet<>();

    public Collection<T> getCollection() {
        return collection;
    }

    @Override
    public CollectionResponse<T> transformToResponse() {
        return new CollectionResponse<>(
                getCollection()
        );
    }

    protected abstract Function<T, Predicate<T>> equalsPredicate();

    public final Storage<T> storeAll(T... elements) {
        Stream.of(elements).forEach(this::store);
        return this;
    }

    public Optional<T> findBy(Predicate<T> predicate) {
        return getCollection()
                .stream()
                .filter(predicate)
                .findFirst();
    }

    public final boolean store(T element) {
        Predicate<T> comparator = equalsPredicate().apply(element);

        Optional<T> duplicate = collection
                .stream()
                .filter(comparator)
                .findFirst();

        if (duplicate.isPresent()) {
            return false;
        } else {
            collection.add(element);
            return true;
        }
    }

    public boolean remove(Predicate<T> isPresentPredicate) {
        return collection.removeIf(isPresentPredicate);
    }
}
