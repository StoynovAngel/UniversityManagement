package com.angel.uni.management.interfaces;

import com.angel.uni.management.utils.exceptions.IncorrectInputException;

public interface IMenu {
    void displayMenu();
    void handleUserChoice() throws IncorrectInputException;
    void handleNavigation(int choice) throws IncorrectInputException;
}
