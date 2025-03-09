package com.angel.uni.management.menu.console.search;

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
        displaySpecifics();
        System.out.print("Please enter your choice (0-6): ");
        int choice = in.nextInt();
        Long id = idHandler();
        getSpecificAttribute(choice, id);
    }
}