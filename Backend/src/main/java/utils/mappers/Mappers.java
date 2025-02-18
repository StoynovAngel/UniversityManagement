package utils.mappers;


/**
 * The {@code Mappers} class provides centralized access to singleton instances of various mappers.
 * <p>
 * This class serves as a utility class that contains getters for retrieving instances of:
 * {@link TeacherMapper}, {@link StudentMapper}, {@link GradeMapper}, {@link SubjectMapper},
 * and {@link GroupMapper}.
 * </p>
 */

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
