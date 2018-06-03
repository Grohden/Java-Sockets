package app.museum.storage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class Storage<T> {
    private final Collection<T> collection = new HashSet<>();

    public Collection<T> getCollection() {
        return collection;
    }

    protected abstract Function<T, Predicate<T>> equalsPredicate();

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

    public void remove(Predicate<T> isPresentPredicate) {
        collection.removeIf(isPresentPredicate);
    }
}
