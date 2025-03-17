package com.angel.uni.management;

import com.angel.uni.management.config.DatabaseInitializer;
import com.angel.uni.management.menu.console.InitialMenu;

public class Main {
    public static void main(String[] args) {
//        If the database is not setup or does not have inserts. Uncomment the 2 lines below 1 by 1.
//        DatabaseInitializer.initializeDatabase();
//        DatabaseInitializer.insertIntoDatabase();
        InitialMenu menu = new InitialMenu();
        menu.execute();
    }
}
