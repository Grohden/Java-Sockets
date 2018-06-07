package app.museum.entities;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Artwork implements Serializable {
    private String artName;
    private String authorName;
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

    public String getAuthorName() {
        return authorName;
    }

    public Artwork setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }

    public Artwork setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
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

}
