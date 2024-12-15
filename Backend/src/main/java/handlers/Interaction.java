package handlers;

import interfaces.IGroup;
import interfaces.IUser;

import java.util.HashMap;
import java.util.Map;

public class Interaction {
    private final IUser userService;
    private final IGroup groupService;
    private final Map<Integer, Runnable> userChoiceToInteract = new HashMap<>();

    public Interaction(IUser userService, IGroup groupService) {
        this.userService = userService;
        this.groupService = groupService;
        populateUserChoiceToInteract();
    }

    public void handleChoice(int choice) {
        Runnable action = userChoiceToInteract.get(choice);
        if (action == null) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        action.run();
    }

    private void populateUserChoiceToInteract() {
        userChoiceToInteract.put(1, groupService::addGroup);
        userChoiceToInteract.put(2, groupService::displaySpecificGroupFromFile);
        userChoiceToInteract.put(3, userService::displayUserFromSpecificGroup);
        userChoiceToInteract.put(4, userService::displayAllUsersFromSpecificGroup);
        userChoiceToInteract.put(5, userService::displaySpecificUserGrades);
        userChoiceToInteract.put(6, userService::addNewUserToGroup);
        userChoiceToInteract.put(7, userService::updateUserGrade);
        userChoiceToInteract.put(8, userService::deleteUserGrade);
        userChoiceToInteract.put(9, userService::addNewGradeToUser);
        userChoiceToInteract.put(10, userService::deleteUserFromSpecificGroup);
        userChoiceToInteract.put(0, () -> System.out.println("Exiting the program..."));
    }
}
