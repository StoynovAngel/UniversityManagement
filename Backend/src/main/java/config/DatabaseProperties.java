package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProperties {
    private final Properties properties;

    public DatabaseProperties() {
        this.properties = new Properties();
        loadProperties();
    }

    public Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("user", properties.getProperty("db.username"));
        props.setProperty("password", properties.getProperty("db.password"));
        props.setProperty("ssl", properties.getProperty("db.ssl"));
        return props;
    }

    public String getDatabaseUrl() {
        return properties.getProperty("db.url");
    }

    public String getCreateTableFilePath() {
        return properties.getProperty("db.file_path");
    }

    public String insertTableFilePath() {
        return properties.getProperty("db.insert_into_tables");
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("database.properties file not found in resources folder.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database.properties file", e);
        }
    }
}
