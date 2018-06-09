package app.museum.entities.person;

import java.io.Serializable;

public abstract class Person<T> implements Serializable {
    protected String name;

    private T getThis() {
        return (T) this;
    }

    public String getName() {
        return name;
    }

    public T setName(String name) {
        this.name = name;
        return getThis();
    }

}
