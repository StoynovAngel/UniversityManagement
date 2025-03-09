package com.angel.uni.management.menu.console.inputs;

import com.angel.uni.management.interfaces.SimpleDTO;

import java.util.Scanner;

public abstract class InputForms<T extends SimpleDTO > {

    protected Scanner in = new Scanner(System.in);

    public abstract T inputTeacherForm();
    public abstract T inputSubjectForm();
    public abstract T inputGradeForm();
    public abstract T inputStudentForm();
    public abstract T inputGroupForm();
}
