package app.museum.entities;

import java.util.Calendar;

public class Painting {
    private String authorName;
    private Calendar paintingDate;
    private String description;

    public Painting() {

    }

    public String getAuthorName() {
        return authorName;
    }

    public Painting setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public Calendar getPaintingDate() {
        return paintingDate;
    }

    public Painting setPaintingDate(Calendar paintingDate) {
        this.paintingDate = paintingDate;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public Painting setDescription(String description) {
        this.description = description;
        return this;
    }
}
