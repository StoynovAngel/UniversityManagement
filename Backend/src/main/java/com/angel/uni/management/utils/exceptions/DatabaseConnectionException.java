package com.angel.uni.management.utils.exceptions;

public class DatabaseConnectionException extends RuntimeException {

    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable e) {
        super(message, e);
    }
}
