package com.angel.uni.management.utils;

public class QueryResult<T> {
    private final T data;
    private final String errorMessage;

    public QueryResult(T data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public boolean hasError() {
        return errorMessage != null;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
