package com.angel.uni.management.utils.exceptions;

public class IncorrectInputException extends Exception {

    public IncorrectInputException(String message) {
        super(message);
    }

    public IncorrectInputException(String message, Throwable e) {
        super(message, e);
    }

}
