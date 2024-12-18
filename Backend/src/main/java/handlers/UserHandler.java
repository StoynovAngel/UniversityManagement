package handlers;

import dto.Group;
import dto.User;
import exceptions.InvalidUserInput;
import exceptions.UserNotFoundException;
import services.GradeService;
import java.util.Scanner;

public class UserHandler {
    private final Scanner in = new Scanner(System.in);
    private final GradeService gradeService;

    public UserHandler(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    public User createUser() {
        String username = getUsernameForm();
        User user = new User(username);
        while (true) {
            gradeService.addGrade(user);
            System.out.println("Add another grade? (y/n): ");
            String choice = in.nextLine().toLowerCase().trim();
            if (!choice.equals("y")) {
                System.out.println("Finished adding grades.");
                break;
            }
        }
        return user;
    }

    public void addGradeUser(Group loadedGroup) {
        gradeService.addGrade(getUserFromGroup(loadedGroup));
    }

    public void deleteUser(Group loadedGroup) {
        loadedGroup.getGroupMembers().remove(getUserFromGroup(loadedGroup));
    }

    public User getUserFromGroup(Group loadedGroup) {
        String username = getUsernameForm();
        return loadedGroup.getGroupMembers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with username '" + username + "' not found in group '" + loadedGroup.getGroupName() + "'."));
    }

    public void updateUserGrade(Group loadedGroup) {
        gradeService.updateGrade(getUserFromGroup(loadedGroup));
    }

    public void deleteUserGrade(Group loadedGroup) {
        gradeService.deleteGrade(getUserFromGroup(loadedGroup));
    }

    private String getUsernameForm() {
        System.out.print("Username: ");
        String username = in.nextLine();
        validateUserInput(username);
        return username;
    }

    private void validateUserInput(String username) {
        if (!username.matches("^[a-zA-Z]{4,}$")) {
            System.out.println("Invalid input. Username must contain only alphabetic characters and be at least 4 letters.");
            throw new InvalidUserInput("Invalid username: " + username);
        }
    }
}
