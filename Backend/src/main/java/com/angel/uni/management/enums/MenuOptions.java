package com.angel.uni.management.enums;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.utils.exceptions.IncorrectInputException;

import java.util.Arrays;
import java.util.Comparator;


public enum MenuOptions {

    EXIT(0, "Exit"),
    SEARCH(1, "Search menu"),
    CREATE(2, "Create menu"),
    DELETE(3, "Delete menu"),
    UPDATE(4, "Update menu");

    private final int optionsNumber;
    private final String description;

    MenuOptions(int optionsNumber, String description) {
        this.optionsNumber = optionsNumber;
        this.description = description;
    }

    public static void displayAllOptions() {
        Arrays.stream(MenuOptions.values())
                .sorted(Comparator.comparingInt(MenuOptions::getOptionsNumber))
                .forEach((menuOptions -> System.out.println(menuOptions.getOptionsNumber() + ". " + menuOptions.getDescription())));
    }

    public static MenuOptions getByOptionNumber(int optionNumber) throws IncorrectInputException {
        for (MenuOptions option : MenuOptions.values()) {
            if (option.getOptionsNumber() == optionNumber) {
                return option;
            }
        }
        String errorMessage = "Invalid option number: " + optionNumber;
        QueryLogger.logError(errorMessage);
        throw new IncorrectInputException(errorMessage);
    }

    public int getOptionsNumber() {
        return optionsNumber;
    }

    public String getDescription() {
        return description;
    }
}
