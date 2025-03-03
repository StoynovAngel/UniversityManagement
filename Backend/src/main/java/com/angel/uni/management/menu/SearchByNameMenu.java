package com.angel.uni.management.menu;

import com.angel.uni.management.interfaces.Menu;
import com.angel.uni.management.utils.container.DependencyContainer;

import java.util.Scanner;

public class SearchByNameMenu extends SearchMenu {
    private final Scanner in = new Scanner(System.in);

    public SearchByNameMenu(DependencyContainer container, Menu initialMenu) {
        super(container, initialMenu);
    }

    public void searchByName() {
        displaySpecifics();
        int choice = in.nextInt();
        if (choice == 0) {
            System.exit(1);
        } else if (choice >= 1 && choice <= 5) {
            String name = nameHandler();
            getSpecificAttributeByName(choice, name);
        } else if (choice == 6) {
            run();
        } else {
            System.err.println("Invalid choice.");
        }
    }

    protected void getSpecificAttributeByName(int choice, String name) {
        switch (choice) {
            case 1 -> searchType(container.getTeacherInstance(), name);
            case 2 -> searchType(container.getStudentInstance(), name);
            case 3 -> searchType(container.getGroupInstance(), name);
            case 4 -> searchType(container.getGradeInstance(), name);
            case 5 -> searchType(container.getSubjectInstance(), name);
            default -> System.err.println("Invalid choice.");
        }
    }
}