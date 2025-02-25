package com.angel.uni.management.services;

import com.angel.uni.management.entity.Teacher;
import com.angel.uni.management.interfaces.TeacherRepository;
import com.angel.uni.management.utils.mappers.Mappers;
import com.angel.uni.management.utils.queries.delete.DeleteQuery;
import com.angel.uni.management.utils.queries.insert.InsertQuery;
import com.angel.uni.management.utils.queries.select.SelectQuery;
import com.angel.uni.management.utils.queries.update.UpdateQuery;

public class TeacherService implements TeacherRepository {
    private static final SelectQuery selectQuery = QueryManager.getSelectQuery();
    private static final UpdateQuery updateQuery = QueryManager.getUpdateQuery();
    private static final InsertQuery insertQuery = QueryManager.getInsertQuery();

    @Override
    public Teacher getTeacherById(Long id) {
        return selectQuery.getTeacherById(id, Mappers.getTeacherMapper());
    }

    @Override
    public void updateTeacherName(String name, Long id) {
        updateQuery.updateTeacherName(name, id);
    }

    @Override
    public void insertTeacher(String name) {
        insertQuery.insertTeacher(name);
    }
}
