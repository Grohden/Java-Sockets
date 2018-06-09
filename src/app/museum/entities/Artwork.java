package app.museum.entities;

import app.museum.entities.person.Author;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Artwork implements Serializable {
    private String artName;
    private Author author;
    private Calendar creationDate;
    private String paintCurrentLocation;
    private String description;

    public String getArtName() {
        return artName;
    }

    public Artwork setArtName(String artName) {
        this.artName = artName;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Artwork setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public Artwork setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Artwork setCreationYear(int creationYear) {
        Calendar c = Calendar.getInstance();

        c.set(Calendar.YEAR, creationYear);

        this.creationDate = c;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Artwork setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPaintCurrentLocation() {
        return paintCurrentLocation;
    }

    public Artwork setPaintCurrentLocation(String paintCurrentLocation) {
        this.paintCurrentLocation = paintCurrentLocation;
        return this;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

        return String.format(
                "Artwork data: \n* Art name: %s \n* Author: %s \n* Year created: %s",
                getArtName(),
                getAuthor(),
                dateFormat.format(getCreationDate().getTime())
        );
    }
}
