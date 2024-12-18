package services;

import handlers.Interaction;
import interfaces.Menu;
import utils.consoleArt.ConsoleArt;
import java.util.Scanner;

public class InitialMenu implements Menu {
    private final Interaction interaction;

    public InitialMenu(Interaction interaction) {
        this.interaction = interaction;
    }

    @Override
    public void show() {
        System.out.println("1. Add a group");
        System.out.println("2. Display specific group.");
        System.out.println("3. Display a user from a group.");
        System.out.println("4. Add user to a group.");
        System.out.println("5. Update user's grade.");
        System.out.println("6. Delete user's grade.");
        System.out.println("7. Add new grade");
        System.out.println("8. Delete user from specific group.");
        System.out.println("0. Exit...");
    }

    @Override
    public void handleUserChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        ConsoleArt.welcomeMessage();
        do {
            show();
            System.out.print("Enter your choice (0-10): ");
            choice = scanner.nextInt();
            interaction.handleChoice(choice);
        } while (choice != 0);
    }
}

