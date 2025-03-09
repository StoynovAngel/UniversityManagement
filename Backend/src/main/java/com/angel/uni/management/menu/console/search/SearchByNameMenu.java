package com.angel.uni.management.menu.console.search;

import java.util.Scanner;

public class SearchByNameMenu extends SearchMenu {
    private static volatile SearchByNameMenu instance;
    private final Scanner in = new Scanner(System.in);

    public static SearchByNameMenu getInstance() {
        if (instance == null) {
            synchronized (SearchByNameMenu.class) {
                if (instance == null) {
                    instance = new SearchByNameMenu();
                }
            }
        }
        return instance;
    }

    public void execute() {
        displaySpecifics();
        System.out.print("Please enter your choice (0-6): ");
        int choice = in.nextInt();
        getSpecificAttribute(choice, nameHandler());
    }
}