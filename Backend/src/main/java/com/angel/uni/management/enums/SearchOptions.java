package com.angel.uni.management.enums;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.interfaces.OptionSelectable;
import com.angel.uni.management.utils.exceptions.IncorrectInputException;

import java.util.Arrays;
import java.util.Comparator;

public enum SearchOptions implements OptionSelectable<SearchOptions> {

    SEARCH_BY_ID(1, "Search by id"),
    SEARCH_BY_NAME(2, "Search by name"),
    RETURN_TO_INITIAL_MENU(3, "Return to initial"),
    EXIT(0, "Exit");

    private final int optionsNumber;
    private final String description;

    SearchOptions(int optionsNumber, String description) {
        this.optionsNumber = optionsNumber;
        this.description = description;
    }

    public int getOptionsNumber() {
        return optionsNumber;
    }

    public String getDescription() {
        return description;
    }

    public static void displayAllOptions() {
        Arrays.stream(SearchOptions.values())
                .sorted(Comparator.comparingInt(SearchOptions::getOptionsNumber))
                .forEach(searchOptions -> System.out.println(searchOptions.getOptionsNumber() + ". " + searchOptions.getDescription()));
    }

    public static SearchOptions getByOptionNumber(int optionNumber) {
        for (SearchOptions option : SearchOptions.values()) {
            if (option.getOptionsNumber() == optionNumber) {
                return option;
            }
        }
        return null;
    }

    @Override
    public int getOptionNumber() {
        return optionsNumber;
    }
}
