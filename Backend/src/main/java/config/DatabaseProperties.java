package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {
    private static final String DATABASE_USERNAME = "db.username";
    private static final String DATABASE_PASSWORD = "db.password";
    private static final String DATABASE_SSL = "db.ssl";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_CREATE_TABLE_FILE_PATH = "db.file_path";
    private static final String DATABASE_INSERT_TABLE_FILE_PATH = "db.insert_into_tables";
    private static final String CONFIG_FILE = "database.properties";
    private static final Properties properties = new Properties();
    private static DatabaseProperties uniqueInstance = new DatabaseProperties();

    private DatabaseProperties() {
        loadProperties();
    }

    public static DatabaseProperties getUniqueInstance() {
        return uniqueInstance;
    }

    public Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("user", properties.getProperty(DATABASE_USERNAME));
        props.setProperty("password", properties.getProperty(DATABASE_PASSWORD));
        props.setProperty("ssl", properties.getProperty(DATABASE_SSL));
        return props;
    }

    public String getDatabaseUrl() {
        return properties.getProperty(DATABASE_URL);
    }

    public String getCreateTableFilePath() {
        return properties.getProperty(DATABASE_CREATE_TABLE_FILE_PATH);
    }

    public String insertTableFilePath() {
        return properties.getProperty(DATABASE_INSERT_TABLE_FILE_PATH);
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("database.properties file not found in resources folder.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database.properties file", e);
        }
    }
}
