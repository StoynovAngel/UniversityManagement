package com.angel.uni.management.menu;

import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.IMenu;
import com.angel.uni.management.utils.container.DependencyContainer;

import java.util.Scanner;

public abstract class Menu implements IMenu, Command {
    protected final Scanner in = new Scanner(System.in);

    protected  <T extends Command> void navigateTo(T menu) {
        if(menu == null) {
            throw new NullPointerException("The menu you are trying to access does not exist");
        }
        menu.execute();
    }

    protected void exitApplication() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    protected long idHandler() {
        System.out.print("Enter valid id: ");
        return in.nextLong();
    }

    protected String nameHandler() {
        System.out.print("Enter valid name: ");
        return in.next();
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
}
