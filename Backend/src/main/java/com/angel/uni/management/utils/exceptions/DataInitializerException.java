package com.angel.uni.management.utils.exceptions;

public class DataInitializerException  extends Exception {

    public DataInitializerException (String message) {
        super(message);
    }

    public DataInitializerException (String message, Throwable e) {
        super(message, e);
    }
}
