package com.angel.uni.management.utils.container;

import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.services.*;
import com.angel.uni.management.utils.queries.delete.DeleteQuery;
import com.angel.uni.management.utils.queries.insert.InsertQuery;
import com.angel.uni.management.utils.queries.select.SelectQuery;
import com.angel.uni.management.utils.queries.update.UpdateQuery;

public class DependencyContainer {
    private static final QueryManager queryManager = new QueryManagerImpl(new SelectQuery(), new UpdateQuery(), new DeleteQuery(), new InsertQuery());

    private static volatile StudentService studentInstance;
    private static volatile TeacherService teacherInstance;
    private static volatile SubjectService subjectInstance;
    private static volatile GroupService groupInstance;
    private static volatile GradeService gradeInstance;
    private static volatile DependencyContainer dependencyContainer;

    public static DependencyContainer getDependencyContainer() {
        if (null == dependencyContainer) {
            synchronized (DependencyContainer.class) {
                if (null == dependencyContainer) {
                    dependencyContainer = new DependencyContainer();
                }
            }
        }
        return dependencyContainer;
    }

    public StudentService getStudentInstance() {
        if (null == studentInstance) {
            synchronized (DependencyContainer.class) {
                if (null == studentInstance) {
                    studentInstance = new StudentService(queryManager);
                }
            }
        }
        return studentInstance;
    }

    public TeacherService getTeacherInstance() {
        if (null == teacherInstance) {
            synchronized (DependencyContainer.class) {
                if (null == teacherInstance) {
                    teacherInstance = new TeacherService(queryManager);
                }
            }
        }
        return teacherInstance;
    }

    public SubjectService getSubjectInstance() {
        if (null == subjectInstance) {
            synchronized (DependencyContainer.class) {
                if (null == subjectInstance) {
                    subjectInstance = new SubjectService(queryManager);
                }
            }
        }
        return subjectInstance;
    }

    public GroupService getGroupInstance() {
        if (null == groupInstance) {
            synchronized (DependencyContainer.class) {
                if (null == groupInstance) {
                    groupInstance = new GroupService(queryManager);
                }
            }
        }
        return groupInstance;
    }

    public GradeService getGradeInstance() {
        if (null == gradeInstance) {
            synchronized (DependencyContainer.class) {
                if (null == gradeInstance) {
                    gradeInstance = new GradeService(queryManager);
                }
            }
        }
        return gradeInstance;
    }
}
