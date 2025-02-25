package com.angel.uni.management;

import com.angel.uni.management.services.StudentService;
import com.angel.uni.management.utils.gson.GsonFormatter;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        GsonFormatter.printObjectToJson(service.getStudentById(1L));
    }
}
