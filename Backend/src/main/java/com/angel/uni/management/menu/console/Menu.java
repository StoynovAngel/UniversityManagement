package com.angel.uni.management.menu.console;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.enums.ClassOptions;
import com.angel.uni.management.enums.MenuOptions;
import com.angel.uni.management.enums.SearchOptions;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.IMenu;
import com.angel.uni.management.interfaces.OptionSelectable;
import com.angel.uni.management.menu.console.create.CreateMenu;
import com.angel.uni.management.menu.console.delete.DeleteMenu;
import com.angel.uni.management.menu.console.search.SearchByIdMenu;
import com.angel.uni.management.menu.console.search.SearchByNameMenu;
import com.angel.uni.management.menu.console.search.SearchMenu;
import com.angel.uni.management.menu.console.update.UpdateMenu;
import com.angel.uni.management.utils.container.DependencyContainer;

import java.util.Scanner;

public abstract class Menu implements IMenu, Command {

    protected final Scanner in = new Scanner(System.in);

    protected <T extends Command> void navigateTo(T menu) {
        if (menu == null) {
            throw new NullPointerException("The menu you are trying to access does not exist");
        }
        menu.execute();
    }

    protected void exitApplication() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    protected Long idHandler() {
        while (true) {
            System.out.print("Enter valid id: ");
            if (in.hasNextLong()) {
                return in.nextLong();
            } else {
                System.out.flush();
                System.out.println("Input is not a valid long. Try again.");
                QueryLogger.logError("Non-long input provided in " + getClass().getSimpleName());
                in.next();
            }
        }
    }

    protected String nameHandler() {
        System.out.print("Enter valid name: ");
        String input = in.nextLine().trim();

        if (input.isEmpty() || input.matches(".*\\d.*")) {
            System.out.println("Input must be a valid non-empty name (no numbers). Try again.");
            QueryLogger.logError("Invalid name input provided in " + getClass().getSimpleName());
            navigateTo(getSearchByIdMenu());
        }
        return input;

    }

    protected DependencyContainer getContainer() {
        return DependencyContainer.getDependencyContainer();
    }

    protected InitialMenu getInitialMenu() {
        return InitialMenu.getInstance();
    }

    protected SearchMenu getSearchMenu() {
        return SearchMenu.getInstance();
    }

    protected SearchByNameMenu getSearchByNameMenu() {
        return SearchByNameMenu.getInstance();
    }

    protected SearchByIdMenu getSearchByIdMenu() {
        return SearchByIdMenu.getInstance();
    }

    protected UpdateMenu getUpdateMenu() {
        return UpdateMenu.getInstance();
    }

    protected CreateMenu getCreateMenu() {
        return CreateMenu.getInstance();
    }

    protected DeleteMenu getDeleteMenu() {
        return DeleteMenu.getInstance();
    }

    protected <T extends Menu> int userChoiceHandler(T menu) {
        if (!in.hasNextInt()) {
            System.out.flush();
            System.out.println("Input is not a valid integer. Try again.");
            QueryLogger.logError("Non-integer input provided in " + getClass().getSimpleName());
            in.nextLine();
            navigateTo(menu);
        }
        return in.nextInt();
    }

    protected <E extends Enum<E> & OptionSelectable<E>> E navigationHandler(Class<E> enumClass, int choice) {
        while (true) {
            E option = OptionSelectable.getByOptionNumber(choice, enumClass.getEnumConstants());
            if (option == null) {
                System.out.print("Choice out of range. Please select a valid option: ");
                if (in.hasNextInt()) {
                    choice = in.nextInt();
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    in.next();
                }
            } else {
                return option;
            }
        }
    }
}
