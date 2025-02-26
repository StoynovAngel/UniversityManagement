package com.angel.uni.management.utils.container;
import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.services.*;
import com.angel.uni.management.utils.queries.delete.DeleteQuery;
import com.angel.uni.management.utils.queries.insert.InsertQuery;
import com.angel.uni.management.utils.queries.select.SelectQuery;
import com.angel.uni.management.utils.queries.update.UpdateQuery;

public class DependencyContainer {
    private final QueryManager queryManager = new QueryManagerImpl(new SelectQuery(), new UpdateQuery(), new DeleteQuery(), new InsertQuery());

    private final StudentService studentService = new StudentService(queryManager);
    private final TeacherService teacherService = new TeacherService(queryManager);
    private final SubjectService subjectService = new SubjectService(queryManager);
    private final GroupService groupService = new GroupService(queryManager);
    private final GradeService gradeService = new GradeService(queryManager);


    public StudentService getStudentService() {
        return studentService;
    }

    public TeacherService getTeacherService() {
        return teacherService;
    }

    public SubjectService getSubjectService() {
        return subjectService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public GradeService getGradeService() {
        return gradeService;
    }
}
