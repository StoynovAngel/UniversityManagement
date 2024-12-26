package queries;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseQuery {
    private final Connection connection;

    public BaseQuery() {
        this.connection = new DatabaseConnection().getConnection();
    }

    protected Connection getConnection() {
        return connection;
    }

    protected void executeStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeUpdate();
    }
    protected PreparedStatement getPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }
}
