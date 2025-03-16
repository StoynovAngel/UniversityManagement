package com.angel.uni.management.utils.exceptions;

public class DatabasePropertiesException extends Exception {

    public DatabasePropertiesException(String message) {
        super(message);
    }

    public DatabasePropertiesException(String message, Throwable e) {
        super(message, e);
    }
}
