package utils.queries;

import config.DatabaseConnection;
import config.QueryLogger;
import java.sql.*;

public class BaseQuery {
    protected Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    protected void executeStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeUpdate();
    }

    protected PreparedStatement getPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

    protected void setParameters(PreparedStatement preparedStatement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }

    protected void inputValidator(Object... params) {
        emptyParamsValidator(params);

        for (Object param : params) {
            nullParamValidator(param);
            emptyStringValidator(param);
            negativeValueIdValidator(param);
        }
    }

    protected void emptyParamsValidator(Object... params) {
        if (checkIfParamsAreEmpty(params)) {
            QueryLogger.logError("Invalid input parameters.");
            throw new IllegalArgumentException("Input parameters cannot be null or empty.");
        }
    }

    private boolean checkIfParamsAreEmpty(Object... params) {
        return params == null || params.length == 0;
    }

    protected void nullParamValidator(Object param) {
        if (checkIfParamIsNull(param)) {
            QueryLogger.logError("Null parameter detected.");
            throw new IllegalArgumentException("Input parameter cannot be null.");
        }
    }

    private boolean checkIfParamIsNull(Object param) {
        return param == null;
    }

    protected void emptyStringValidator(Object param) {
        if (checkIfParamIsEmptyString(param)) {
            QueryLogger.logError("Empty string parameter detected.");
            throw new IllegalArgumentException("String parameters cannot be empty.");
        }
    }

    private boolean checkIfParamIsEmptyString(Object param) {
        return param instanceof String str && str.trim().isEmpty();
    }

    protected void negativeValueIdValidator(Object param) {
        if (checkIfIdIsNegative(param)) {
            QueryLogger.logError("Negative id parameter detected.");
            throw new IllegalArgumentException("Invalid input: id cannot be negative.");
        }
    }

    private boolean checkIfIdIsNegative(Object param) {
        return param instanceof Long id && (id < 0);
    }
}
