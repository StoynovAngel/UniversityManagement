package entity;

import utils.exceptions.InvalidUserInput;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String username;

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
        usernameValidation(username);
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    protected void usernameValidation(String username) {
        if (!username.matches("^[a-zA-Z]{4,}$")) {
            System.out.println("Invalid input. Username must contain only alphabetic characters and be at least 4 letters.");
            throw new InvalidUserInput("Invalid username: " + username);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
