package com.angel.uni.management.utils.queries.select;

import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.entity.*;
import com.angel.uni.management.interfaces.CustomRowMapper;
import com.angel.uni.management.utils.QueryResult;
import com.angel.uni.management.utils.exceptions.DataRetrievalException;
import com.angel.uni.management.utils.exceptions.DatabaseConnectionException;
import com.angel.uni.management.utils.exceptions.QueryExecutionException;
import com.angel.uni.management.utils.mappers.*;
import com.angel.uni.management.utils.queries.QueryExecutor;

import java.util.List;
import java.util.Optional;

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

    public Optional<Group> getGroupById(Long id, GroupMapper mapper) {
        return resultToOptional(SelectStatements.selectGroupByIdSql(), mapper, id);
    }

    public Optional<Subject> getSubjectById(Long id, SubjectMapper mapper) {
        return resultToOptional(SelectStatements.selectSubjectByIdSql(), mapper, id);
    }

    public Optional<Subject> getSubjectByName(String name, SubjectMapper mapper) {
        return resultToOptional(SelectStatements.selectSubjectByNameSql(), mapper, name);
    }

    public Optional<Teacher> getTeacherById(Long id, TeacherMapper mapper) {
        return resultToOptional(SelectStatements.selectTeacherByIdSql(), mapper, id);
    }

    public Optional<Student> getStudentById(Long id, StudentMapper mapper) {
        return resultToOptional(SelectStatements.selectStudentByIdSql(), mapper, id);
    }

    public Optional<Student> getStudentByUsername(String username, StudentMapper mapper) {
        return resultToOptional(SelectStatements.selectStudentByUsernameSql(), mapper, username);
    }

    public Optional<Grade> getGradeByName(String name, GradeMapper mapper) {
        return resultToOptional(SelectStatements.selectGradeByGradeNameSql(), mapper, name);
    }

    public Optional<List<Grade>> getGradesByStudentName(String name, GradeMapper mapper) {
        try {
            List<Grade> grades = executeQueryList(SelectStatements.selectGradesByStudentNameSql(), mapper, name);
            return Optional.ofNullable(grades);
        } catch (IllegalArgumentException e) {
            QueryLogger.logError("Illegal argument: " + e.getMessage());
            return Optional.empty();
        } catch (QueryExecutionException e) {
            QueryLogger.logError("Failed to retrieve grades for student: " + name, e);
            return Optional.empty();
        } catch (DatabaseConnectionException e) {
            QueryLogger.logError("Connection failed to be established. Message: " + e.getMessage());
            return Optional.empty();
        }
    }
    private <T> Optional<T> resultToOptional(String sqlStatement, CustomRowMapper<?, T> mapper, Object... param) {
        QueryResult<T> result = executeSelectQuery(sqlStatement, mapper, param);

        if (result.hasError()) {
            System.err.println("Sorry, we couldn't retrieve the necessary information. Please try again later.");
            return Optional.empty();
        }
        QueryLogger.logMessage(sqlStatement);
        return Optional.ofNullable(result.getData());
    }
}