package com.angel.uni.management.config;

public class DatabaseConstants {
    public static final String DATABASE_USERNAME = "db.username";
    public static final String DATABASE_PASSWORD = "db.password";
    public static final String DATABASE_SSL = "db.ssl";
    public static final String DATABASE_URL = "db.url";
    public static final String DATABASE_CREATE_TABLE_FILE_PATH = "db.file_path";
    public static final String DATABASE_INSERT_TABLE_FILE_PATH = "db.insert_into_tables";
    public static final String CONFIG_FILE = "database.properties";

    private DatabaseConstants() {
        throw new UnsupportedOperationException("Cannot initialize this class: " + getClass().getSimpleName());
    }
}
