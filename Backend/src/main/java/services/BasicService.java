package services;

import utils.queries.select.SelectQuery;
import utils.queries.update.UpdateQuery;

public class BasicService {
    protected final SelectQuery selectQuery = new SelectQuery();
    protected final UpdateQuery updateQuery = new UpdateQuery();
}
