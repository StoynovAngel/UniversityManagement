package utils.handlers;

import dto.User;

import java.util.Scanner;

public class UserHandler {
    private final Scanner in = new Scanner(System.in);

    public User createUser() {
        String username = getUsernameForm();
        return new User(username);
    }

    private String getUsernameForm() {
        System.out.print("Username: ");
        return in.nextLine();
    }
}
