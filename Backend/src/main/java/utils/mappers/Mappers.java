package utils.mappers;

public class Mappers {

    private Mappers() {
        throw new UnsupportedOperationException("Should not instantiate " + getClass().getSimpleName());
    }

    public static TeacherMapper getTeacherMapper() {
        return TeacherMapper.getUniqueInstance();
    }

    public static StudentMapper getStudentMapper() {
        return StudentMapper.getUniqueInstance();
    }

    public static GradeMapper getGradeMapper() {
        return GradeMapper.getUniqueInstance();
    }

    public static SubjectMapper getSubjectMapper() {
        return SubjectMapper.getUniqueInstance();
    }

    public static GroupMapper getGroupMapper() {
        return GroupMapper.getUniqueInstance();
    }
}
