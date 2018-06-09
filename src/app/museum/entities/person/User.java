package app.museum.entities.person;

import app.console.Menu;
import app.museum.interfaces.CommandLineCreatable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User extends Person<User> implements CommandLineCreatable<User> {

    private String email;
    private Calendar registryDate;

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return String.format(
                "User data: \n* Name: %s \n* Email: %s \n* Registry date: %s",
                name,
                email,
                dateFormat.format(registryDate.getTime())
        );
    }

    public Calendar getRegistryDate() {
        return registryDate;
    }

    public User setRegistryDate(Calendar registryDate) {
        this.registryDate = registryDate;
        return this;
    }

    @Override
    public User requestDataFromConsole() {
        return this
                .setName(Menu.prompt("Inform user name: "))
                .setEmail(Menu.prompt("Inform user email: "))
                .setRegistryDate(Calendar.getInstance());
    }
}
