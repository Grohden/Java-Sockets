package app.museum.entities.person;

public class Author extends Person {

    public Author(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
