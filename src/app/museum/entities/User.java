package app.museum.entities;

import app.console.Menu;

import java.io.Serializable;
import java.util.Calendar;

public class User implements Serializable {

    private String name;
    private String email;
    private Calendar registryDate;

    public static User promptUser() {
        return new User()
                .setName(Menu.prompt("Inform user name: "))
                .setEmail(Menu.prompt("Inform user email: "))
                .setRegistryDate(Calendar.getInstance());
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return name + " : " + email + " : " + registryDate.toInstant();
    }

    public Calendar getRegistryDate() {
        return registryDate;
    }

    public User setRegistryDate(Calendar registryDate) {
        this.registryDate = registryDate;
        return this;
    }

}
