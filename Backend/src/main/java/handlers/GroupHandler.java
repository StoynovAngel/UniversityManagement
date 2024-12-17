package handlers;

import dto.Grade;
import dto.Group;
import dto.User;
import exceptions.*;
import services.FileService;
import services.GradeService;
import services.UserService;

import java.util.List;
import java.util.Scanner;

public class GroupHandler {
    private final Scanner in = new Scanner(System.in);
    private final FileService fileService;
    private final GradeService gradeService = new GradeService(new GradeHandler());
    private final UserService userService = new UserService(new UserHandler(gradeService));

    public GroupHandler(FileService fileService) {
        this.fileService = fileService;
    }

    public void displaySpecificGroupFromFile() {
        System.out.print("Enter a group name: ");
        String searchedGroupName = in.nextLine();
        validateGroupUserInput(searchedGroupName);
        Group loadedGroup = getSpecificLoadedGroupByName(searchedGroupName);
        System.out.println(loadedGroup);
    }

    public void addGroup() {
        System.out.print("Enter group name: ");
        String groupName = in.nextLine();
        validateGroupUserInput(groupName);

        List<Group> loadedGroups = loadAllGroups();
        validateFindFileGroupName(loadedGroups, groupName);

        Group newGroup = new Group(groupName);
        saveGroup(newGroup);
    }

    public void addNewUserToGroup() {
        Group group = getGroup();
        List<User> users = group.getGroupMembers();
        User newUser = userService.createUser();

        if (isUserAlreadyInGroup(users, newUser)) {
            System.out.println("This user already exists...");
            return;
        }

        users.add(newUser);
        saveGroup(group);
        System.out.println("User successfully added and group saved!");
    }

    public Group getGroup() {
        System.out.print("Enter group name: ");
        String searchedGroupName = in.nextLine();
        Group group = loadGroup(searchedGroupName);

        if (groupNameHandler(group, searchedGroupName)) {
            throw new GroupNotFoundException("Could not find group with the name: " + searchedGroupName);
        }

        return group;
    }

    public void displaySpecificUserGrades() {
        System.out.println(getSpecificUser().getGrades());
    }

    public void displayUserFromSpecificGroup() {
        System.out.println(getSpecificUser());
    }

    public void updateUserGrade() {
        Group specificGroup = getGroup();

        System.out.println("What grade do you want to change? Write the subject's name: ");
        String inputSubject = in.nextLine();

        Grade filteredGrade = getFilteredGrade(specificGroup, inputSubject);

        gradeService.updateGrade(filteredGrade);
        saveGroup(specificGroup);
    }

    public void deleteUserGrade() {
        Group specificGroup = getGroup();

        System.out.println("What grade do you want to change? Write the subject's name: ");
        String inputSubject = in.nextLine();

        if (removeGradeIfInGroup(specificGroup, inputSubject)) {
            saveGroup(specificGroup);
            System.out.println("Grade removed and group saved.");
            return;
        }
        throw new GradeNotFound("No such grade found");
    }

    public void deleteUserFromSpecificGroup() {
        Group specificGroup = getGroup();
        User user = getUserFromGroup(specificGroup);
        specificGroup.getGroupMembers().remove(user);
        saveGroup(specificGroup);
    }

    public void addNewGradeToUser() {
        Group specificGroup = getGroup();
        User user = getUserFromGroup(specificGroup);
        List<Grade> grades = user.getGrades();
        gradeService.addGrade(grades);
        saveGroup(specificGroup);
    }

    private void saveGroup(Group group){
        fileService.saveGroupToFile(group);
    }

    private Group loadGroup(String searchedName) {
        return fileService.loadGroup(searchedName);
    }

    private List<Group> loadAllGroups() {
        return fileService.loadAllGroups();
    }

    private boolean isUserAlreadyInGroup(List<User> users, User newUser) {
        return users.stream().anyMatch(user -> user.getUsername().equals(newUser.getUsername()));
    }

    private boolean groupNameHandler(Group group, String searchedGroupName) {
        return group == null || !group.getGroupName().equals(searchedGroupName);
    }

    private void validateGroupUserInput(String groupName) {
        if (groupName.isEmpty()) {
            throw new InvalidUserInput("It must contain at least a letter");
        }
    }

    private void validateFindFileGroupName(List<Group> loadedGroups, String groupName) {
        if (doesGroupExist(loadedGroups, groupName)) {
            throw new GroupAlreadyExists("Group with this name already exists.");
        }
    }

    private boolean doesGroupExist(List<Group> loadedGroups, String nameOfNewGroup) {
        return loadedGroups.stream()
                .anyMatch(group -> group.getGroupName().equalsIgnoreCase(nameOfNewGroup));
    }

    private Group getSpecificLoadedGroupByName(String searchedGroupName) {
        Group loadedGroup = loadGroup(searchedGroupName);
        if (loadedGroup == null) {
            throw new InvalidGroup("Group with this name could not be find.");
        }
        return loadedGroup;
    }

    private Grade getFilteredGrade(Group specificGroup, String inputSubject) {
        User user = getUserFromGroup(specificGroup);
        List<Grade> grades = user.getGrades();
        return grades.stream()
                .filter(grade -> grade.getSubject().equals(inputSubject))
                .findFirst()
                .orElseThrow(() -> new GradeNotFound("No such subject found - " + inputSubject));
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
        Group specificGroup = getGroup();
        return getUserFromGroup(specificGroup);
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
}
