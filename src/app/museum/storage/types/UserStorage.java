package app.museum.storage.types;

import app.museum.entities.person.User;
import app.museum.storage.Storage;

import java.util.function.Function;
import java.util.function.Predicate;

public class UserStorage extends Storage<User> {
    private static UserStorage instance;

    private UserStorage() {
        super();
    }

    public static UserStorage get() {
        if (instance == null) {
            instance = new UserStorage();
        }

        return instance;
    }

    @Override
    protected Function<User, Predicate<User>> equalsPredicate() {
        return baseComparator -> compared -> baseComparator.getEmail().equals(compared.getEmail());
    }
}
