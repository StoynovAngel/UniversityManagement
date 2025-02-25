package com.angel.uni.management.services;

import com.angel.uni.management.utils.queries.delete.DeleteQuery;
import com.angel.uni.management.utils.queries.insert.InsertQuery;
import com.angel.uni.management.utils.queries.select.SelectQuery;
import com.angel.uni.management.utils.queries.update.UpdateQuery;

/**
 *  The class provides centralized access to CRUD query operations
 *  for database interactions. It serves as a utility class containing instances of
 *  {@link SelectQuery}, {@link UpdateQuery}, {@link DeleteQuery}, and {@link InsertQuery}.
 *  This class uses private constructor to prevent initialization.
 */

public class QueryManager {
    private static final SelectQuery selectQuery = new SelectQuery();
    private static final UpdateQuery updateQuery = new UpdateQuery();
    private static final DeleteQuery deleteQuery = new DeleteQuery();
    private static final InsertQuery insertQuery = new InsertQuery();

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
