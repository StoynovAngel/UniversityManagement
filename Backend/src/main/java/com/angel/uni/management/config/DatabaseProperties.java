package com.angel.uni.management.config;

import com.angel.uni.management.utils.exceptions.DatabaseConnectionException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  DatabaseProperties sets the database properties. It implements eager singleton.
 *  <p>
 *  This class prevents instantiation and provides a static method
 *  {@link #getInstance()} to obtain the properties.
 *  </p>
 */

public class DatabaseProperties {
    private static final Properties properties = new Properties();
    private static volatile DatabaseProperties instance;

    private DatabaseProperties() {
        loadProperties();
        if (instance != null) {
            throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
        }
    }

    public static DatabaseProperties getInstance() {
        if (instance == null) {
            synchronized (DatabaseProperties.class) {
                if (instance == null) {
                    instance = new DatabaseProperties();
                }
            }
        }
        return instance;
    }

    public Properties getProperties() throws DatabaseConnectionException {
        Properties props = new Properties();
        try {
            props.setProperty("user", properties.getProperty(DatabaseConstants.DATABASE_USERNAME));
            props.setProperty("password", properties.getProperty(DatabaseConstants.DATABASE_PASSWORD));
            props.setProperty("ssl", properties.getProperty(DatabaseConstants.DATABASE_SSL));
        } catch (IllegalArgumentException e) {
            QueryLogger.logError("Invalid database property found: ", e.getMessage());
            throw new DatabaseConnectionException("Invalid database property found:", e);
        }
        return props;
    }

    public String getDatabaseUrl() {
        return properties.getProperty(DatabaseConstants.DATABASE_URL);
    }

    public String getCreateTableFilePath() {
        return properties.getProperty(DatabaseConstants.DATABASE_CREATE_TABLE_FILE_PATH);
    }

    public String insertTableFilePath() {
        return properties.getProperty(DatabaseConstants.DATABASE_INSERT_TABLE_FILE_PATH);
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(DatabaseConstants.CONFIG_FILE)) {
            if (input == null) {
                throw new FileNotFoundException(DatabaseConstants.CONFIG_FILE +" file not found in resources folder.");
            }
            properties.load(input);
        } catch (IOException e) {
            QueryLogger.logError("Failed to load " + DatabaseConstants.CONFIG_FILE + " file", e);
        }
    }
}
