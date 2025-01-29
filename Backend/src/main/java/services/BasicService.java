package services;

import utils.queries.delete.DeleteQuery;
import utils.queries.insert.InsertQuery;
import utils.queries.select.SelectQuery;
import utils.queries.update.UpdateQuery;

public class BasicService {
    protected final SelectQuery selectQuery = new SelectQuery();
    protected final UpdateQuery updateQuery = new UpdateQuery();
    protected final DeleteQuery deleteQuery = new DeleteQuery();
    protected final InsertQuery insertQuery = new InsertQuery();
}
