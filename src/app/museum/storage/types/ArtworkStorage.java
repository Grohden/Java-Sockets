package app.museum.storage.types;

import app.museum.entities.Artwork;
import app.museum.storage.Storage;

import java.util.function.Function;
import java.util.function.Predicate;

public class ArtworkStorage extends Storage<Artwork> {
    private static ArtworkStorage instance;

    private ArtworkStorage() {
        super();
    }

    public static ArtworkStorage get() {
        if (instance == null) {
            instance = new ArtworkStorage();
        }

        return instance;
    }

    @Override
    protected Function<Artwork, Predicate<Artwork>> equalsPredicate() {
        return base -> artwork -> {
            final Boolean isAuthorSame = base.getAuthor().equals(artwork.getAuthor());
            final Boolean isNameEquals = base.getArtName().equals(artwork.getArtName());

            return isAuthorSame && isNameEquals;
        };
    }
}
