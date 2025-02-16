package config;

import java.io.IOException;
import java.util.logging.*;

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

    private static void loggerInitializer() {
        try {
            loggerSpecifics(new FileHandler(LOG_FILE, true));
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize QueryLogger. Logging will not work.", e);
        }
    }

    private static void loggerSpecifics(FileHandler fileHandler) {
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
        LOGGER.setUseParentHandlers(false);
        LOGGER.setLevel(Level.INFO);
    }
}
