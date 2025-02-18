package utils.queries;

import config.QueryLogger;
import utils.exceptions.DataUpdateException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class QueryUpdater extends BaseQuery{

    public void updateSingleRow(String sql, Object... params) {
        inputValidator(params);
        try (PreparedStatement preparedStatement = getPreparedStatement(sql)) {
            setParameters(preparedStatement, params);
            executeStatement(preparedStatement);
            System.out.println("Update successful.");
        } catch (SQLException e) {
            QueryLogger.logError("SQL error while updating row. Query: " + sql + " Params: " + Arrays.toString(params), e);
            throw new DataUpdateException("Error executing update query.", e);
        }
    }
}
