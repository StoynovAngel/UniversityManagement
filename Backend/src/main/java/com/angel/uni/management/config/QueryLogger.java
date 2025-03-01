package com.angel.uni.management.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

/**
 * QueryLogger contains static methods used to log queries and display error messages.
 * This class uses private constructor to prevent initialization.
 */

public class QueryLogger {
    private static final Logger LOGGER = Logger.getLogger(QueryLogger.class.getName());
    private static final String LOG_FILE = "query_logs.txt";

    static {
        loggerInitializer();
    }

    private QueryLogger() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }

    public static void logQuery(String sql, Object... params) {
        if (sql == null || sql.trim().isEmpty()) {
            throw new IllegalArgumentException("SQL query cannot be null or empty.");
        }
        if (params == null) {
            throw new IllegalArgumentException("Params array cannot be null.");
        }
        StringBuilder logMessage = new StringBuilder("Executing Query: " + sql + " | Params: [");

        for (int i = 0; i < params.length; i++) {
            logMessage.append(params[i]);
            if (i < params.length - 1) {
                logMessage.append(", ");
            }
        }
        logMessage.append("]");
        LOGGER.info(logMessage.toString());
    }

    public static void logMessage(String message) {
        LOGGER.info(message);
    }

    public static void logDebug(String message) {
        LOGGER.log(Level.INFO, message);
    }

    public static void logError(String message) {
        LOGGER.log(Level.SEVERE, message);
    }

    public static void logError(String message, Exception e) {
        String errorMessage = message + " | Exception: " + e.getMessage();
        LOGGER.log(Level.SEVERE, errorMessage, e);
    }

    public static void logError(String message, String exceptionBody) {
        LOGGER.log(Level.SEVERE, message, exceptionBody);
    }

    public static void logError(String methodName, String sql, Object[] params, Exception e) {
        String errorMessage = String.format(
                "Error in %s | Query: %s | Params: %s | Error: %s", methodName, sql, Arrays.toString(params), e.getMessage()
        );
        QueryLogger.logError(errorMessage, e);
    }

    private static void loggerInitializer() {
        try {
            File file = new File(LOG_FILE);
            if (!file.exists()) {
                throw new FileNotFoundException("File with this name does not exists: " + LOG_FILE);
            }
            loggerSpecifics(new FileHandler(LOG_FILE, true));
        } catch (IOException e) {
            System.out.println("Query logger does not save exceptions in a specific file. It will console log.");
            LOGGER.addHandler(new ConsoleHandler());
            LOGGER.setUseParentHandlers(true);
        }
    }

    private static void loggerSpecifics(FileHandler fileHandler) {
        if (fileHandler == null) {
            throw new IllegalArgumentException("FileHandler cannot be null.");
        }
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
        LOGGER.setLevel(Level.INFO);
        LOGGER.setUseParentHandlers(false);
    }
}
