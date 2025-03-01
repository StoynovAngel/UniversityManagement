package com.angel.uni.management.services;
import com.angel.uni.management.entity.Subject;
import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.interfaces.SubjectRepository;
import com.angel.uni.management.utils.mappers.Mappers;

import java.util.Optional;

public class SubjectService implements SubjectRepository {
    private final QueryManager queryManager;

    public SubjectService(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public Optional<Subject> getSubjectById(Long id) {
        return queryManager.selectQuery().getSubjectById(id, Mappers.getSubjectMapper());
    }

    @Override
    public Optional<Subject> getSubjectByName(String name) {
        return queryManager.selectQuery().getSubjectByName(name, Mappers.getSubjectMapper());
    }

    @Override
    public void updateSubjectDescriptionBySubjectName(String description, String name) {
        queryManager.updateQuery().updateSubjectDescription(description, name);
    }

    @Override
    public void deleteSubject(Long id) {
        queryManager.deleteQuery().deleteSubjectById(id);
    }

    @Override
    public void insertSubject(String name, int hours_per_week, String teacherName, String description) {
        queryManager.insertQuery().insertSubject(name, hours_per_week, teacherName, description);
    }

    @Override
    public void insertStudentIntoSubject(String subjectName, String studentUsername) {
        queryManager.insertQuery().insertStudentIntoSubject(subjectName, studentUsername);
    }
}
