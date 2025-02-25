package com.angel.uni.management.utils.queries;

import com.angel.uni.management.config.QueryLogger;
import java.util.Objects;

/**
 * Provides utility methods for validating input parameters.
 * <p>
 * This class is a static utility class and cannot be instantiated.
 * It ensures that input parameters are valid by checking for - emptyStrings, nullParameters, negative id number.
 * </p>
 */

public class QueryValidator {

    private QueryValidator() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }

    public static void inputValidator(Object... params) {
        if (params == null || params.length == 0) {
            throw new IllegalArgumentException("Parameters cannot be null or empty");
        }

        for (Object param : params) {
            Objects.requireNonNull(param, "Input parameter cannot be null.");

            if (param instanceof String && ((String) param).trim().isEmpty()) {
                throw new IllegalArgumentException("String parameter cannot be empty");
            }

            if (param instanceof Number && ((Number) param).longValue() < 0) {
                throw new IllegalArgumentException("Numeric parameter cannot be negative");
            }
        }
    }

    public static void emptyParamsValidator(Object... params) {
        if (params.length == 0) {
            QueryLogger.logError("Invalid input parameters.");
            throw new IllegalArgumentException("Input parameters cannot be null or empty.");
        }
    }

    public static void nullParamValidator(Object param) {
        if (param == null) {
            QueryLogger.logError("Null parameter detected.");
            throw new IllegalArgumentException("Input parameter cannot be null.");
        }
    }

    public static void emptyStringValidator(Object param) {
        if (param instanceof String str && str.trim().isEmpty()) {
            QueryLogger.logError("Empty string parameter detected.");
            throw new IllegalArgumentException("String parameters cannot be empty.");
        }
    }

    public static void negativeValueIdValidator(Object param) {
        if (param instanceof Long id && (id < 0)) {
            QueryLogger.logError("Negative id parameter detected.");
            throw new IllegalArgumentException("Invalid input: id cannot be negative.");
        }
    }
}