package utils.validation;

import dto.Grade;
import dto.Group;
import dto.User;
import utils.exceptions.GroupAlreadyExists;
import utils.exceptions.InvalidUserInput;

import java.util.List;

public class Validator {

    protected boolean isGradeRemoved(List<Grade> grades, String subject) {
        return grades.removeIf(grade -> grade.getSubject().equalsIgnoreCase(subject));
    }

    protected boolean doesGroupExist(List<Group> loadedGroups, String nameOfNewGroup) {
        return loadedGroups.stream()
                .anyMatch(group -> group.getGroupName().equalsIgnoreCase(nameOfNewGroup));
    }

    protected <T extends User> boolean isUserAlreadyInGroup(List<T> users, User newUser) {
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
