package app.museum.storage;

import app.museum.entities.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class UserStorage {
    private static Collection<User> userList = new HashSet<>();

    public static void removeUser(User user) {
        userList.removeIf(listUser -> listUser.getEmail().equals(user.getEmail()));
    }

    public static Collection<User> getAll(){
        return userList;
    }

    /**
     * Inserts a new inser in the userList only if there's no other
     * user using the user email
     *
     * @param newUser new user to be added
     * @return if the add operation was successful
     */
    public static boolean addUser(User newUser) {
        Optional<User> sameEmailGuy = userList
                .stream()
                .filter(user -> user.getEmail().equals(newUser.getEmail()))
                .findFirst();

        if (sameEmailGuy.isPresent()) {
            return false;
        } else {
            userList.add(newUser);
            return true;
        }
    }

    public Collection<User> getUserList() {
        return userList;
    }
}
