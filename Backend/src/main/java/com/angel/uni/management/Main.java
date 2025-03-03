package com.angel.uni.management;

import com.angel.uni.management.menu.InitialMenu;
import com.angel.uni.management.utils.container.DependencyContainer;

public class Main {
    public static void main(String[] args) {
        DependencyContainer dependencyContainer = new DependencyContainer();
        InitialMenu menu = new InitialMenu(dependencyContainer);
        menu.run();
    }
}
