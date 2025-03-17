package com.angel.uni.management.menu.console.search;

import com.angel.uni.management.enums.ClassOptions;

public class SearchByIdMenu extends SearchMenu {
    private static volatile SearchByIdMenu instance;

    public static SearchByIdMenu getInstance() {
        if (instance == null) {
            synchronized (SearchByIdMenu.class) {
                if (instance == null) {
                    instance = new SearchByIdMenu();
                }
            }
        }
        return instance;
    }

    public void execute() {
        while (true) {
            displaySpecifics();
            System.out.print("Please enter your choice (0-6): ");

            if (!in.hasNextInt()) {
                System.out.println("Input is not a valid integer. Try again.");
                in.nextLine();
                continue;
            }

            int choice = in.nextInt();
            in.nextLine();

            if (choice < 0 || choice > ClassOptions.values().length - 1) {
                System.out.println("Choice out of range. Please select a valid option.");
                continue;
            }

            if (choice == 0) {
                exitApplication();
                return;
            } else if (choice == 6) {
                getInitialMenu().execute();
                return;
            }

            Long id = idHandler();
            getSpecificAttribute(choice, id);

            System.out.print("Search again? (1 = Yes, 0 = No): ");
            if (in.hasNextInt()) {
                int searchAgain = in.nextInt();
                in.nextLine();
                if (searchAgain == 0) {
                    getInitialMenu().execute();
                    return;
                }
            } else {
                System.out.println("Invalid input. Returning to the main menu.");
                getInitialMenu().execute();
                return;
            }
        }
    }
}