package com.angel.uni.management.interfaces;

import com.angel.uni.management.utils.queries.delete.DeleteQuery;
import com.angel.uni.management.utils.queries.insert.InsertQuery;
import com.angel.uni.management.utils.queries.select.SelectQuery;
import com.angel.uni.management.utils.queries.update.UpdateQuery;

public interface QueryManager {
    SelectQuery selectQuery();
    UpdateQuery updateQuery();
    DeleteQuery deleteQuery();
    InsertQuery insertQuery();
}
