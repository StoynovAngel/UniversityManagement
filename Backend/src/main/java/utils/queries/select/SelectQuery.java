package utils.queries.select;

import entity.*;
import utils.mappers.*;
import utils.queries.QueryExecutor;

import java.util.List;

public class SelectQuery extends QueryExecutor {
    public SelectQuery() {
        super();
    }

    public Subject getSubjectById(Long id, SubjectMapper mapper) {
        return executeSelect(SelectStatements.selectSubjectByIdSql(), mapper, id);
    }

    public Subject getSubjectByName(String name, SubjectMapper mapper) {
        return executeSelect(SelectStatements.selectSubjectByNameSql(), mapper, name);
    }

    public Teacher getTeacherById(Long id, TeacherMapper mapper) {
        return executeSelect(SelectStatements.selectTeacherByIdSql(), mapper, id);
    }

    public Student getStudentById(Long id, StudentMapper mapper) {
        return executeSelect(SelectStatements.selectStudentByIdSql(), mapper, id);
    }

    public Grade getGradeByName(String name, GradeMapper mapper) {
        return executeSelect(SelectStatements.selectGradeByGradeNameSql(), mapper, name);
    }

    public List<Grade> getGradeByStudentName(String name, GradeMapper mapper) {
        return executeQueryList(SelectStatements.selectGradesByStudentNameSql(), mapper, name);
    }
}