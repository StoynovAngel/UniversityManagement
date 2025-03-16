package com.angel.uni.management.utils;

public class QueryResult<T> {
    private final T data;

    public QueryResult(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
