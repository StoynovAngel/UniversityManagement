package services;

import dto.Grade;
import dto.Group;
import dto.User;
import exceptions.GradeNotFound;
import exceptions.GroupNotFoundException;
import exceptions.InvalidUserInput;
import exceptions.UserNotFoundException;
import interfaces.IUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService implements IUser {
    private final Scanner in = new Scanner(System.in);
    private final FileService fileService;
    private final GradeService gradeService = new GradeService();

    public UserService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void displaySpecificUserGrades() {
        System.out.println(getSpecificUser().getGrades());
    }

    @Override
    public void displayUserFromSpecificGroup() {
        System.out.println(getSpecificUser());
    }

    @Override
    public void displayAllUsersFromSpecificGroup() {
        Group loadedGroup = getSpecificGroupFromFile();
        List<User> members = loadedGroup.getGroupMembers();

        if (members.isEmpty()) {
            System.out.println("No users found in this group.");
        } else {
            members.forEach(System.out::println);
        }
    }

    @Override
    public void updateUserGrade() {
        Group specificGroup = getSpecificGroupFromFile();

        System.out.println("What grade do you want to change? Write the subject's name: ");
        String inputSubject = in.nextLine();

        Grade filteredGrade = getFilteredGrade(specificGroup, inputSubject);

        gradeService.updateGrade(filteredGrade);
        fileService.saveGroupToFile(specificGroup);
    }

    @Override
    public void deleteUserGrade() {
        Group specificGroup = getSpecificGroupFromFile();

        System.out.println("What grade do you want to change? Write the subject's name: ");
        String inputSubject = in.nextLine();

        if (removeGradeIfInGroup(specificGroup, inputSubject)) {
            fileService.saveGroupToFile(specificGroup);
            System.out.println("Grade removed and group saved.");
            return;
        }
        throw new GradeNotFound("No such grade found");
    }

    @Override
    public void deleteUserFromSpecificGroup() {
        Group specificGroup = getSpecificGroupFromFile();
        User user = getUserFromGroup(specificGroup);
        specificGroup.getGroupMembers().remove(user);
        fileService.saveGroupToFile(specificGroup);
    }

    @Override
    public void addNewUserToGroup() {
        Group group = getSpecificGroupFromFile();
        List<User> users = group.getGroupMembers();
        User newUser = createUser();

        if (isUserAlreadyInGroup(users, newUser)) {
            System.out.println("This user already exists...");
            return;
        }

        users.add(newUser);
        fileService.saveGroupToFile(group);
        System.out.println("User successfully added and group saved!");
    }

    @Override
    public void addNewGradeToUser() {
        Group specificGroup = getSpecificGroupFromFile();
        User user = getUserFromGroup(specificGroup);
        List<Grade> grades = user.getGrades();
        gradeService.addGrade(grades);
        fileService.saveGroupToFile(specificGroup);
    }

    private Grade getFilteredGrade(Group specificGroup, String inputSubject) {
        User user = getUserFromGroup(specificGroup);
        List<Grade> grades = user.getGrades();
        return grades.stream()
                .filter(grade -> grade.getSubject().equals(inputSubject))
                .findFirst()
                .orElseThrow(() -> new GradeNotFound("No such subject found - " + inputSubject));
    }

    private boolean isUserAlreadyInGroup(List<User> users, User newUser) {
        return users.stream().anyMatch(user -> user.getUsername().equals(newUser.getUsername()));
    }

    private boolean removeGradeIfInGroup(Group specificGroup, String inputSubject) {
        User user = getUserFromGroup(specificGroup);
        List<Grade> grades = user.getGrades();
        return grades.removeIf(grade -> gradeHandler(grade, inputSubject));
    }

    private boolean gradeHandler(Grade grade, String subject) {
        return grade != null && grade.getSubject().equals(subject);
    }

    private User getSpecificUser() {
        Group specificGroup = getSpecificGroupFromFile();
        return getUserFromGroup(specificGroup);
    }

    private Group getSpecificGroupFromFile() {
        System.out.print("Enter group name: ");
        String searchedGroupName = in.nextLine();
        Group group = fileService.loadGroup(searchedGroupName);

        if (groupNameHandler(group, searchedGroupName)) {
            throw new GroupNotFoundException("Could not find group with the name: " + searchedGroupName);
        }

        return group;
    }

    private boolean groupNameHandler(Group group, String searchedGroupName) {
        return group == null || !group.getGroupName().equals(searchedGroupName);
    }

    private User getUserFromGroup(Group loadedGroup) {
        List<User> usersFromGroup = loadedGroup.getGroupMembers();
        System.out.print("Enter username: ");
        String searchedUsername = in.nextLine();
        validateUserInput(searchedUsername);

        return usersFromGroup.stream()
                .filter(user -> user.getUsername().equals(searchedUsername))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with username '" + searchedUsername + "' not found."));
    }

    private void validateUserInput(String username) {
        if (!username.matches("^[a-zA-Z]{4,}$")) {
            System.out.println("Invalid input. Username must contain only alphabetic characters and be at least 4 letters.");
            throw new InvalidUserInput("Invalid username: " + username);
        }
    }

    private User createUser() {
        System.out.print("Username: ");
        String username = in.nextLine();
        User user = new User(username);
        while (true) {
            Grade newGrade = gradeService.addGradeToUser();
            user.getGrades().add(newGrade);

            System.out.println("Add another grade? (y/n): ");
            String choice = in.nextLine().toLowerCase().trim();
            if (!choice.equals("y")) {
                System.out.println("Finished adding grades.");
                break;
            }
        }
        return user;
    }
}
