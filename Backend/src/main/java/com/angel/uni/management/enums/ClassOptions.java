package com.angel.uni.management.enums;

import com.angel.uni.management.interfaces.OptionSelectable;

import java.util.Arrays;
import java.util.Comparator;

public enum ClassOptions implements OptionSelectable<ClassOptions> {

    TEACHER(1, "Teacher"),
    SUBJECT(2, "Subject"),
    GRADE(3, "Grade"),
    STUDENT(4, "Student"),
    GROUP(5, "Group"),
    RETURN_TO_INITIAL_MENU(6, "Return to initial"),
    EXIT(0, "Exit");

    private final int optionsNumber;
    private final String description;

    ClassOptions(int optionsNumber, String description) {
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
        Arrays.stream(ClassOptions.values())
                .sorted(Comparator.comparingInt(ClassOptions::getOptionsNumber))
                .forEach(classOptions -> System.out.println(classOptions.getOptionsNumber() + ". " + classOptions.getDescription()));
    }

    public static void displayDeleteMenu() {
        Arrays.stream(ClassOptions.values())
                .filter(classOptions -> classOptions.equals(RETURN_TO_INITIAL_MENU) || classOptions.equals(SUBJECT) || classOptions.equals(EXIT))
                .sorted(Comparator.comparingInt(ClassOptions::getOptionsNumber))
                .forEach(classOptions -> System.out.println(classOptions.getOptionsNumber() + ". " + classOptions.getDescription()));
    }

    public static ClassOptions getByOptionNumber(int optionNumber) {
        for (ClassOptions option : ClassOptions.values()) {
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
