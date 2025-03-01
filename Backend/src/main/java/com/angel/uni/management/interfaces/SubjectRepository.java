package com.angel.uni.management.interfaces;

import com.angel.uni.management.entity.Subject;

import java.util.Optional;

public interface SubjectRepository {
    Optional<Subject> getSubjectById(Long id);
    Optional<Subject> getSubjectByName(String name);
    void updateSubjectDescriptionBySubjectName(String description, String name);
    void deleteSubject(Long id);
    void insertSubject(String name, int hours_per_week, String teacherName, String description);
    void insertStudentIntoSubject(String subjectName, String studentUsername);
}
