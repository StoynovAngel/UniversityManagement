package com.angel.uni.management.dto;

import com.angel.uni.management.enums.GradeType;
import java.util.Date;

public record GradeDTO(String name, StudentDTO student, TeacherDTO teacher, GradeType gradeType, double mark, Date dateOfGrading) {
}
