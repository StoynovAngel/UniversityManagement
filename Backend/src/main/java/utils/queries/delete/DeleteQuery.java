package utils.queries.delete;

import config.QueryLogger;
import utils.exceptions.DataUpdateException;
import utils.queries.QueryUpdater;

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
            throw new DataUpdateException("Delete failed: No subject found with this id: " + id);
        }
    }
}
