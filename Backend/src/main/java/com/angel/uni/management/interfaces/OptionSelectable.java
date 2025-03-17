package com.angel.uni.management.interfaces;

public interface OptionSelectable<T> {
    static <E extends Enum<E> & OptionSelectable<E>> E getByOptionNumber(int optionNumber, E... values) {
        for (E option : values) {
            if (option.getOptionNumber() == optionNumber) {
                return option;
            }
        }
        return null;
    }
    int getOptionNumber();
}
