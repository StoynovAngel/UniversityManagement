package com.angel.uni.management;

import com.angel.uni.management.entity.Student;
import com.angel.uni.management.services.StudentService;
import com.angel.uni.management.utils.container.DependencyContainer;
import com.angel.uni.management.utils.gson.GsonFormatter;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        DependencyContainer container = new DependencyContainer();
        StudentService studentService = container.getStudentService();
        Optional<Student> studentOptional = studentService.getStudentById(1L);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            GsonFormatter.printObjectToJson(student);
        } else {
            System.out.println("Sorry we couldn't return such student");
        }
    }
}
