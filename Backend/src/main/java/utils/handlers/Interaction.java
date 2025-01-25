package utils.handlers;

import enums.UserAction;
import interfaces.GroupRepository;

public class Interaction {
    private final GroupRepository groupService;

    public Interaction(GroupRepository groupService) {
        this.groupService = groupService;
    }

    public void handleChoice(int choice) {
        UserAction action = UserAction.fromChoice(choice);
        if (action == null) {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        action.execute(groupService);
    }

    public void displayMenu() {
        System.out.println("Available Actions:");
        for (UserAction action : UserAction.values()) {
            System.out.println(action.getChoice() + ": " + action.getDescription());
        }
    }
}
