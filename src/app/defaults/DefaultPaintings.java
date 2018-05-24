package app.defaults;

import app.museum.Painting;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class DefaultPaintings {
    private DefaultPaintings() {

    }

    static Set<Painting> get() {
        final HashSet<Painting> defaultsSet = new HashSet<>();

        final Painting firstPaint = new Painting()
                .setAuthorName("Picasso")
                .setDescription("Some paintings from picasso")
                .setPaintingDate(Calendar.getInstance());

        defaultsSet.add(firstPaint);
        return defaultsSet;
    }
}
