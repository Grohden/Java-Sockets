package app.museum.storage.types;

import app.museum.entities.person.Author;
import app.museum.storage.Storage;

import java.util.function.Function;
import java.util.function.Predicate;

public class AuthorStorage extends Storage<Author> {
    private static AuthorStorage instance;

    private AuthorStorage() {
        super();
    }

    public static AuthorStorage get() {
        if (instance == null) {
            instance = new AuthorStorage();
        }

        return instance;
    }

    @Override
    protected Function<Author, Predicate<Author>> equalsPredicate() {
        return baseAuthor -> author -> baseAuthor.getName().equals(author.getName());
    }
}
