package com.angel.uni.management.utils.queries.select;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.entity.*;
import com.angel.uni.management.utils.exceptions.DataRetrievalException;
import com.angel.uni.management.utils.mappers.*;
import com.angel.uni.management.utils.queries.QueryExecutor;
import java.util.List;

/**
 * The class provides methods for executing SELECT operations in the database.
 * <p>
 * This class extends {@link QueryExecutor} and provides a method to select different classes by different parameters.
 * If the selection fails, an error is logged, and a {@link DataRetrievalException} is thrown.
 * </p>
 */

public class SelectQuery extends QueryExecutor {

    public SelectQuery() {
        super();
    }

    public Group getGroupById(Long id, GroupMapper mapper) {
        try {
            return executeSelect(SelectStatements.selectGroupByIdSql(), mapper, id);
        } catch (Exception e) {
            QueryLogger.logError("Unexpected error while retrieving Group's id: " + id, e);
            throw new DataRetrievalException("Unexpected error retrieving Group's id: " + id, e);
        }
    }

    public Subject getSubjectById(Long id, SubjectMapper mapper) {
        try {
            return executeSelect(SelectStatements.selectSubjectByIdSql(), mapper, id);
        } catch (Exception e) {
            QueryLogger.logError("Unexpected error while retrieving Subject's id: " + id, e);
            throw new DataRetrievalException("Unexpected error retrieving Subject's id: " + id, e);
        }
    }

    public Subject getSubjectByName(String name, SubjectMapper mapper) {
        try {
            return executeSelect(SelectStatements.selectSubjectByNameSql(), mapper, name);
        } catch (Exception e) {
            QueryLogger.logError("Unexpected error while retrieving Subject's name: " + name, e);
            throw new DataRetrievalException("Unexpected error retrieving Subject's name: " + name, e);
        }
    }

    public Teacher getTeacherById(Long id, TeacherMapper mapper) {
        try {
            return executeSelect(SelectStatements.selectTeacherByIdSql(), mapper, id);
        } catch (Exception e) {
            QueryLogger.logError("Unexpected error while retrieving Teacher's id: " + id, e);
            throw new DataRetrievalException("Unexpected error retrieving Teacher's id: " + id, e);
        }
    }

    public Student getStudentById(Long id, StudentMapper mapper) {
        try {
            return executeSelect(SelectStatements.selectStudentByIdSql(), mapper, id);
        } catch (Exception e) {
            QueryLogger.logError("Unexpected error while retrieving Student's id: " + id, e);
            throw new DataRetrievalException("Unexpected error retrieving Student's id: " + id, e);
        }
    }

    public Student getStudentByUsername(String username, StudentMapper mapper) {
        try {
            return executeSelect(SelectStatements.selectStudentByUsernameSql(), mapper, username);
        } catch (Exception e) {
            QueryLogger.logError("Unexpected error while retrieving Student's username: " + username, e);
            throw new DataRetrievalException("Unexpected error retrieving Student's username: " + username, e);
        }
    }

    public Grade getGradeByName(String name, GradeMapper mapper) {
        try {
            return executeSelect(SelectStatements.selectGradeByGradeNameSql(), mapper, name);
        } catch (Exception e) {
            QueryLogger.logError("Unexpected error while retrieving Grade's name: " + name, e);
            throw new DataRetrievalException("Unexpected error retrieving Grade's name: " + name, e);
        }
    }

    public List<Grade> getGradesByStudentName(String name, GradeMapper mapper) {
        try {
            return executeQueryList(SelectStatements.selectGradesByStudentNameSql(), mapper, name);
        } catch (Exception e) {
            QueryLogger.logError("Unexpected error while retrieving Grades for Student's name: " + name, e);
            throw new DataRetrievalException("Unexpected error retrieving Grades for Student's name: " + name, e);
        }
    }
}