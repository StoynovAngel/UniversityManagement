package com.angel.uni.management.services;

import com.angel.uni.management.entity.Student;
import com.angel.uni.management.interfaces.StudentRepository;
import com.angel.uni.management.utils.mappers.Mappers;
import com.angel.uni.management.utils.queries.insert.InsertQuery;
import com.angel.uni.management.utils.queries.select.SelectQuery;
import com.angel.uni.management.utils.queries.update.UpdateQuery;

public class StudentService implements StudentRepository {
    private static final SelectQuery selectQuery = QueryManager.getSelectQuery();
    private static final UpdateQuery updateQuery = QueryManager.getUpdateQuery();
    private static final InsertQuery insertQuery = QueryManager.getInsertQuery();

    @Override
    public Student getStudentById(Long id) {
        return selectQuery.getStudentById(id, Mappers.getStudentMapper());
    }

    @Override
    public Student getStudentByUsername(String username) {
        return selectQuery.getStudentByUsername(username, Mappers.getStudentMapper());
    }

    @Override
    public void updateStudentUsername(String username, Long id) {
        updateQuery.updateStudentUsername(username, id);
    }

    @Override
    public void insertStudent(String username) {
        insertQuery.insertStudent(username);
    }
}
