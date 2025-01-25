package utils.mappers;

import dto.SubjectDTO;
import entity.*;
import enums.GradeType;
import interfaces.CustomRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class SubjectMapper extends Mappers implements CustomRowMapper<SubjectDTO, Subject> {

    public Subject mapToEntity(SubjectDTO subjectDTO) {
        return entityForm(subjectDTO);
    }

    public SubjectDTO mapToDTO(Subject subject) {
        return dtoForm(subject);
    }

    @Override
    public Subject mapRow(ResultSet resultSet) throws SQLException {
        return mapForm(resultSet);
    }

    private Subject entityForm(SubjectDTO subjectDTO) {
        Subject newSubject = new Subject();
        newSubject.setName(subjectDTO.name());
        newSubject.setHoursPerWeek(subjectDTO.hoursPerWeek());
        newSubject.setDescription(subjectDTO.description());
        newSubject.setTeacher(getTeacherMapper().mapToEntity(subjectDTO.teacher()));
        newSubject.setStudentsAssignedToSubject(subjectDTO.students().stream().map(getStudentMapper()::mapToEntity).toList());
        return newSubject;
    }

    private SubjectDTO dtoForm(Subject subject) {
        return new SubjectDTO(
                subject.getName(),
                subject.getHoursPerWeek(),
                subject.getDescription(),
                getTeacherMapper().mapToDTO(subject.getTeacher()),
                subject.getStudentsAssignedToSubject().stream().map(getStudentMapper()::mapToDTO).toList()
        );
    }

    private Subject mapForm(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("subject_id"));
        subject.setName(resultSet.getString("subject_name"));
        subject.setHoursPerWeek(resultSet.getInt("hours_per_week"));
        subject.setDescription(resultSet.getString("description"));

        Teacher teacher = mapTeacher(resultSet);
        subject.setTeacher(teacher);

        List<Student> students = mapStudents(resultSet);
        subject.setStudentsAssignedToSubject(students);

        return subject;
    }

    private Teacher mapTeacher(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("teacher_id"));
        teacher.setName(resultSet.getString("teacher_name"));
        return teacher;
    }

    private List<Student> mapStudents(ResultSet resultSet) throws SQLException {
        Map<Long, Student> studentMap = new HashMap<>();

        do {
            Long studentId = resultSet.getLong("student_id");
            if (studentId != 0) {
                Student student = studentMap.computeIfAbsent(studentId, id -> {
                    Student newStudent = new Student();
                    try {
                        newStudent.setId(resultSet.getLong("student_id"));
                        newStudent.setUsername(resultSet.getString("student_username"));
                        newStudent.setGrades(new ArrayList<>());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return newStudent;
                });

                if (resultSet.getObject("grade_id") != null) {
                    Grade grade = mapGrade(resultSet);
                    student.getGrades().add(grade);
                }
            }
        } while (resultSet.next());

        return new ArrayList<>(studentMap.values());
    }

    private Grade mapGrade(ResultSet resultSet) throws SQLException {
        Grade grade = new Grade();
        grade.setId(resultSet.getLong("grade_id"));
        grade.setName(resultSet.getString("grade_name"));
        grade.setMark(resultSet.getDouble("grade_mark"));
        grade.setGradeType(GradeType.valueOf(resultSet.getString("grade_type")));
        grade.setDateOfGrading(resultSet.getDate("date_of_grading"));
        return grade;
    }
}
