package utils.validation;

import dto.Grade;
import dto.Group;
import dto.User;
import utils.exceptions.GroupAlreadyExists;
import utils.exceptions.InvalidUserInput;

import java.util.List;

public class Validation {

    protected void gradeValidation(double mark) {
        if (mark <= 2 || mark > 6) {
            throw new InvalidUserInput("Mark must be between 2 and 6");
        }
    }

    protected void usernameValidation(String username) {
        if (!username.matches("^[a-zA-Z]{4,}$")) {
            System.out.println("Invalid input. Username must contain only alphabetic characters and be at least 4 letters.");
            throw new InvalidUserInput("Invalid username: " + username);
        }
    }

    protected boolean isGradeRemoved(List<Grade> grades, String subject) {
        return grades.removeIf(grade -> grade.getSubject().equalsIgnoreCase(subject));
    }

    protected boolean doesGroupExist(List<Group> loadedGroups, String nameOfNewGroup) {
        return loadedGroups.stream()
                .anyMatch(group -> group.getGroupName().equalsIgnoreCase(nameOfNewGroup));
    }

    protected boolean isUserAlreadyInGroup(List<User> users, User newUser) {
        return users.stream().anyMatch(user -> user.getUsername().equals(newUser.getUsername()));
    }

    protected boolean groupNameHandler(Group group, String searchedGroupName) {
        return group == null || !group.getGroupName().equals(searchedGroupName);
    }

    protected void validateGroupUserInput(String groupName) {
        if (groupName.isEmpty()) {
            throw new InvalidUserInput("It must contain at least a letter");
        }
    }

    protected void validateFindFileGroupName(List<Group> loadedGroups, String groupName) {
        if (doesGroupExist(loadedGroups, groupName)) {
            throw new GroupAlreadyExists("Group with this name already exists.");
        }
    }
}
