package utils.queries;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryUpdater extends BaseQuery{
    public void updateSingleRow(String sql, Object... params) {
        try (PreparedStatement preparedStatement = getPreparedStatement(sql)) {
            setParameters(preparedStatement, params);
            executeStatement(preparedStatement);
            System.out.println("Update successful.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
