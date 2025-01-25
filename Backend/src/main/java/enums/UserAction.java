package enums;

import interfaces.GroupRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public enum UserAction {
    EXIT(0, group -> System.out.println("Exiting the program..."), "Exit the program..."),;

    private int choice;
    private Consumer<GroupRepository> action;
    private String description;

    public void execute(GroupRepository group) {
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
