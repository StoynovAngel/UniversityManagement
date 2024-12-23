package enums;

import interfaces.IGroup;

import java.util.function.Consumer;

public enum UserAction {
    ADD_GROUP(1, IGroup::addGroup, "Add a group."),
    DISPLAY_SPECIFIC_GROUP(2, IGroup::displaySpecificGroupFromFile, "Display specific group."),
    DISPLAY_USER_FROM_GROUP(3, IGroup::displayUserFromSpecificGroup, "Display a user from a group."),
    ADD_NEW_USER(4, IGroup::addNewUserToGroup, "Add user to a group."),
    UPDATE_USER_GRADE(5, IGroup::updateUserGradeAndSaveToFile, "Update user's grade."),
    DELETE_USER_GRADE(6, IGroup::deleteUserGradeAndSaveToFile, "Delete user's grade."),
    ADD_NEW_GRADE(7, IGroup::addNewGradeToUserAndSaveToFile, "Add new grade"),
    DELETE_USER_FROM_GROUP(8, IGroup::deleteUserFromSpecificGroup, "Delete user from specific group."),
    EXIT(0, group -> System.out.println("Exiting the program..."), "Exit the program..."),;

    private final int choice;
    private final Consumer<IGroup> action;
    private final String description;

    UserAction(int choice, Consumer<IGroup> action, String description) {
        this.choice = choice;
        this.action = action;
        this.description = description;
    }

    public int getChoice() {
        return choice;
    }

    public String getDescription() {
        return description;
    }

    public void execute(IGroup group) {
        action.accept(group);
    }

    public static UserAction fromChoice(int choice) {
        for (UserAction action : values()) {
            if (action.getChoice() == choice) {
                return action;
            }
        }
        return null;
    }
}
