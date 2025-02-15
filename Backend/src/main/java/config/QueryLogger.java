package config;

import java.io.IOException;
import java.util.logging.*;

public class QueryLogger {
    private static final Logger LOGGER = Logger.getLogger(QueryLogger.class.getName());
    private static final String LOG_FILE = "query_logs.txt";

    static {
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE, true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
        }
    }

    public static void logQuery(String sql, Object... params) {
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

    public static void logError(String message, Exception e) {
        LOGGER.log(Level.SEVERE, message, e);
    }
}
