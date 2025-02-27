package com.angel.uni.management.utils.exceptions;

public class DataAccessException extends Exception {

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable e) {
        super(message, e);
    }
}
