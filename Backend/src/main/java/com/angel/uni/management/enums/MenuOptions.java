package com.angel.uni.management.enums;

public enum MenuOptions {

    RETURN_TO_INITIAL_MENU(1, "Return to initial"),
    TEACHER(2, "Teacher"),
    SUBJECT(3, "Subject"),
    GRADE(4, "Grade"),
    STUDENT(5, "Student"),
    GROUP(6, "Group"),
    EXIT(0, "Exit"),
    SEARCH(1, "Search menu"),
    CREATE(2, "Create menu"),
    DELETE(3, "Delete menu"),
    UPDATE(4, "Update menu");

    private final int optionsNumber;
    private final String description;
    private static final String BASE_MENUS_PATTERN = "menu";

    MenuOptions(int optionsNumber, String description) {
        this.optionsNumber = optionsNumber;
        this.description = description;
    }

    public static void displaySpecifics() {
        for (MenuOptions option : MenuOptions.values()) {
            if (!option.description.contains(BASE_MENUS_PATTERN)) {
                System.out.println(option.getOptionsNumber() + ". "
                        + option.getDescription());
            }
        }
    }

    public static void displayBaseMenu() {
        for (MenuOptions option : MenuOptions.values()) {
            if (option.description.contains(BASE_MENUS_PATTERN) || option.description.contains(EXIT.description)) {
                System.out.println(option.getOptionsNumber() + ". "
                        + option.getDescription());
            }
        }
    }

    public static MenuOptions getByOptionNumber(int optionNumber) {
        for (MenuOptions option : MenuOptions.values()) {
            if (option.getOptionsNumber() == optionNumber) {
                return option;
            }
        }
        throw new IllegalArgumentException("Invalid option number: " + optionNumber);
    }

    public int getOptionsNumber() {
        return optionsNumber;
    }

    public String getDescription() {
        return description;
    }
}
