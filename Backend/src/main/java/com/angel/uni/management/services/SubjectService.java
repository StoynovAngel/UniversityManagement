package com.angel.uni.management.services;
import com.angel.uni.management.entity.Subject;
import com.angel.uni.management.interfaces.SubjectRepository;
import com.angel.uni.management.utils.mappers.Mappers;
import com.angel.uni.management.utils.queries.delete.DeleteQuery;
import com.angel.uni.management.utils.queries.insert.InsertQuery;
import com.angel.uni.management.utils.queries.select.SelectQuery;
import com.angel.uni.management.utils.queries.update.UpdateQuery;

public class SubjectService implements SubjectRepository {
    private static final SelectQuery selectQuery = QueryManager.getSelectQuery();
    private static final UpdateQuery updateQuery = QueryManager.getUpdateQuery();
    private static final InsertQuery insertQuery = QueryManager.getInsertQuery();
    private static final DeleteQuery deleteQuery = QueryManager.getDeleteQuery();

    @Override
    public Subject getSubjectById(Long id) {
        return selectQuery.getSubjectById(id, Mappers.getSubjectMapper());
    }

    @Override
    public Subject getSubjectByName(String name) {
        return selectQuery.getSubjectByName(name, Mappers.getSubjectMapper());
    }

    @Override
    public void updateSubjectDescriptionBySubjectName(String description, String name) {
        updateQuery.updateSubjectDescription(description, name);
    }

    @Override
    public void deleteSubject(Long id) {
        deleteQuery.deleteSubjectById(id);
    }

    @Override
    public void insertSubject(String name, int hours_per_week, String teacherName, String description) {
        insertQuery.insertSubject(name, hours_per_week, teacherName, description);
    }

    @Override
    public void insertStudentIntoSubject(String subjectName, String studentUsername) {
        insertQuery.insertStudentIntoSubject(subjectName, studentUsername);
    }
}
