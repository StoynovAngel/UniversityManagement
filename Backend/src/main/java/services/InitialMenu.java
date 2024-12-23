package services;

import utils.handlers.Interaction;
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
        interaction.displayMenu();
    }

    @Override
    public void userChoice() {
        handleChoice();
    }

    private void handleChoice() {
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

