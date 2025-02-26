package com.angel.uni.management.services;

import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.utils.queries.delete.DeleteQuery;
import com.angel.uni.management.utils.queries.insert.InsertQuery;
import com.angel.uni.management.utils.queries.select.SelectQuery;
import com.angel.uni.management.utils.queries.update.UpdateQuery;

/**
 * The class provides centralized access to CRUD query operations
 * for database interactions. It serves as a utility class containing instances of
 * {@link SelectQuery}, {@link UpdateQuery}, {@link DeleteQuery}, and {@link InsertQuery}.
 * This class uses private constructor to prevent initialization.
 */

public class QueryManagerImpl implements QueryManager {
    private final SelectQuery selectQuery;
    private final UpdateQuery updateQuery;
    private final DeleteQuery deleteQuery;
    private final InsertQuery insertQuery;

    public QueryManagerImpl(SelectQuery selectQuery, UpdateQuery updateQuery, DeleteQuery deleteQuery, InsertQuery insertQuery) {
        this.selectQuery = selectQuery;
        this.updateQuery = updateQuery;
        this.deleteQuery = deleteQuery;
        this.insertQuery = insertQuery;
    }

    @Override
    public SelectQuery selectQuery() {
        return selectQuery;
    }

    @Override
    public UpdateQuery updateQuery() {
        return updateQuery;
    }

    @Override
    public DeleteQuery deleteQuery() {
        return deleteQuery;
    }

    @Override
    public InsertQuery insertQuery() {
        return insertQuery;
    }
}