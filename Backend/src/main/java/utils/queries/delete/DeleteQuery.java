package utils.queries.delete;

import config.QueryLogger;
import utils.exceptions.DataUpdateException;
import utils.queries.QueryUpdater;

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
