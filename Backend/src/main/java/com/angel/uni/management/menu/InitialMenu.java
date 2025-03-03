package com.angel.uni.management.menu;

import com.angel.uni.management.interfaces.Menu;
import com.angel.uni.management.utils.container.DependencyContainer;

import java.util.Scanner;

public class InitialMenu implements Menu {
    private final Scanner in = new Scanner(System.in);
    private final DependencyContainer container;

    public InitialMenu(DependencyContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        handleUserChoice();
    }

    @Override
    public void displayMenu() {
        System.out.println("""
                Options:
                1. Search menu
                2. Create menu
                3. Delete menu
                4. Update menu
                0. Exit
                """);
    }

    @Override
    public void handleUserChoice() {
        displayMenu();
        System.out.print("Please enter your choice(0-4): ");
        int choice = in.nextInt();
        handleNavigation(choice);
    }

    @Override
    public void handleNavigation(int choice) {
        switch (choice) {
            case 0 -> {
                System.out.println("Byeee...");
                System.exit(1);
            }
            case 1 -> {
                Menu searchMenu = new SearchMenu(container, this);
                searchMenu.run();
            }
            default -> System.err.println("Incorrect choice provided " + choice + ". It must be between (0-4)");
        }
    }
}
