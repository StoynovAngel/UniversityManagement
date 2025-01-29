package utils.queries.delete;

import utils.queries.QueryUpdater;

public class DeleteQuery extends QueryUpdater {
    public void deleteSubjectById(Long id) {
        updateSingleRow(DeleteStatements.deleteSubjectById(), id);
    }
}
