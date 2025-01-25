package config;

import io.github.cdimascio.dotenv.Dotenv;
import java.util.Properties;

public class DatabaseProperties {
    private final Dotenv dotenv;

    public DatabaseProperties() {
        this.dotenv = Dotenv.load();
    }

    public Properties getProperties() {
        String user = dotenv.get("SQL_USERNAME");
        String password = dotenv.get("PASSWORD");
        String ssl = dotenv.get("SSL");

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("ssl", ssl);
        return props;
    }

    public String getDatabaseUrl() {
        return dotenv.get("URL");
    }

    public String getCreateTableFilePath() {
        return dotenv.get("FILE_PATH");
    }

    public String insertTableFilePath() {
        return dotenv.get("INSERT_INTO_TABLES");
    }
}
