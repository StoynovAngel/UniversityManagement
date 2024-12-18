package handlers;

import interfaces.IGroup;
import java.util.HashMap;
import java.util.Map;

public class Interaction {
    private final IGroup groupService;
    private final Map<Integer, Runnable> userChoiceToInteract = new HashMap<>();

    public Interaction(IGroup groupService) {
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
        userChoiceToInteract.put(3, groupService::displayUserFromSpecificGroup);
        userChoiceToInteract.put(4, groupService::addNewUserToGroup);
        userChoiceToInteract.put(5, groupService::updateUserGradeAndSaveToFile);
        userChoiceToInteract.put(6, groupService::deleteUserGradeAndSaveToFile);
        userChoiceToInteract.put(7, groupService::addNewGradeToUserAndSaveToFile);
        userChoiceToInteract.put(8, groupService::deleteUserFromSpecificGroup);
        userChoiceToInteract.put(0, () -> System.out.println("Exiting the program..."));
    }
}
