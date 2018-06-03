package app.museum.storage.types;

import app.museum.entities.Painting;
import app.museum.storage.Storage;

import java.util.function.Function;
import java.util.function.Predicate;

public class PaintingStorage extends Storage<Painting> {
    private static PaintingStorage instance;

    private PaintingStorage() {
        super();
    }

    public static PaintingStorage get() {
        if (instance != null) {
            instance = new PaintingStorage();
        }

        return instance;
    }

    @Override
    protected Function<Painting, Predicate<Painting>> equalsPredicate() {
        return base -> painting -> {
            final Boolean isAuthorSame = base.getAuthorName().equals(painting.getAuthorName());
            final Boolean isNameEquals = base.getName().equals(painting.getName());

            return isAuthorSame && isNameEquals;
        };
    }
}
