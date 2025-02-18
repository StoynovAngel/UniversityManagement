package services;

import utils.queries.delete.DeleteQuery;
import utils.queries.insert.InsertQuery;
import utils.queries.select.SelectQuery;
import utils.queries.update.UpdateQuery;

/**
 *  The class provides centralized access to CRUD query operations
 *  for database interactions. It serves as a utility class containing instances of
 *  {@link SelectQuery}, {@link UpdateQuery}, {@link DeleteQuery}, and {@link InsertQuery}.
 *  This class uses private constructor to prevent initialization.
 */

public class QueryManager {
    private static SelectQuery selectQuery = new SelectQuery();
    private static UpdateQuery updateQuery = new UpdateQuery();
    private static DeleteQuery deleteQuery = new DeleteQuery();
    private static InsertQuery insertQuery = new InsertQuery();

    private QueryManager() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }

    public static SelectQuery getSelectQuery() {
        return selectQuery;
    }

    public static UpdateQuery getUpdateQuery() {
        return updateQuery;
    }

    public static DeleteQuery getDeleteQuery() {
        return deleteQuery;
    }

    public static InsertQuery getInsertQuery() {
        return insertQuery;
    }
}
