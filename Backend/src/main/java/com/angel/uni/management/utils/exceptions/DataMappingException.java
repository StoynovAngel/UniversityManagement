package com.angel.uni.management.utils.exceptions;

public class DataMappingException extends Exception {

    public DataMappingException(String message) {
        super(message);
    }

    public DataMappingException(Throwable e) {
        super(e);
    }

    public DataMappingException(String message, Throwable e) {
        super(message, e);
    }
}
