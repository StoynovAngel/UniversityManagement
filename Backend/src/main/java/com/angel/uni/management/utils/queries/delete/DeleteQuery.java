package com.angel.uni.management.utils.queries.delete;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.utils.exceptions.DataUpdateException;
import com.angel.uni.management.utils.queries.QueryUpdater;

/**
 * The class provides methods for executing DELETE operations in the database.
 * <p>
 * This class extends {@link QueryUpdater} and provides a method to delete by different parameters.
 * If the deletion fails, an error is logged, and a {@link DataUpdateException} is thrown.
 * </p>
 */

public class DeleteQuery extends QueryUpdater {

    public void deleteSubjectById(Long id) {
        try {
            updateSingleRow(DeleteStatements.deleteSubjectById(), id);
        } catch (Exception e) {
            QueryLogger.logError("Failed to delete subject with id: " + id, e);
        }
    }
}
