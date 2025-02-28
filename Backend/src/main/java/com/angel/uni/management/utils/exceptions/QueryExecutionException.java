package com.angel.uni.management.utils.exceptions;

public class QueryExecutionException extends Exception {

    public QueryExecutionException(String message) {
        super(message);
    }

    public QueryExecutionException(String message, Throwable e) {
        super(message, e);
    }
}
