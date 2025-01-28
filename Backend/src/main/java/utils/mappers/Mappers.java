package utils.mappers;

public class Mappers {
    private TeacherMapper teacherMapper;
    private StudentMapper studentMapper;
    private GradeMapper gradeMapper;
    private SubjectMapper subjectMapper;
    private GroupMapper groupMapper;

    public Mappers() {}

    protected TeacherMapper getTeacherMapper() {
        if (teacherMapper == null) {
            teacherMapper = new TeacherMapper();
        }
        return teacherMapper;
    }

    protected StudentMapper getStudentMapper() {
        if (studentMapper == null) {
            studentMapper = new StudentMapper();
        }
        return studentMapper;
    }

    protected GradeMapper getGradeMapper() {
        if (gradeMapper == null) {
            gradeMapper = new GradeMapper();
        }
        return gradeMapper;
    }

    protected SubjectMapper getSubjectMapper() {
        if (subjectMapper == null) {
            subjectMapper = new SubjectMapper();
        }
        return subjectMapper;
    }

    protected GroupMapper getGroupMapper() {
        if (groupMapper == null) {
            groupMapper = new GroupMapper();
        }
        return groupMapper;
    }

}
