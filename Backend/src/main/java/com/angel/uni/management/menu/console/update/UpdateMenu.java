package com.angel.uni.management.menu.console.update;

import com.angel.uni.management.command.UpdateCommand;
import com.angel.uni.management.config.QueryLogger;
import com.angel.uni.management.dto.update.*;
import com.angel.uni.management.enums.ClassOptions;
import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.menu.console.Menu;
import com.angel.uni.management.menu.console.inputs.UpdateForm;

public class UpdateMenu extends Menu implements Command {

    private static volatile UpdateMenu instance;
    private final UpdateForm updateForm = new UpdateForm();

    public static UpdateMenu getInstance() {
        if (instance == null) {
            synchronized (UpdateMenu.class) {
                if (instance == null) {
                    instance = new UpdateMenu();
                }
            }
        }
        return instance;
    }

    @Override
    public void execute() {
        while (true) {
            displayMenu();
            handleUserChoice();
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("Update menu: ");
        ClassOptions.displayAllOptions();
    }

    @Override
    public void handleUserChoice() {
        System.out.print("Please enter your choice (0-6): ");
        int choice = userChoiceHandler(getUpdateMenu());
        handleNavigation(choice);
    }

    @Override
    public void handleNavigation(int choice) {
        ClassOptions option = navigationHandler(ClassOptions.class, choice);
        switch (option) {
            case RETURN_TO_INITIAL_MENU -> navigateTo(getInitialMenu());
            case TEACHER -> updateTeacherName();
            case SUBJECT -> updateSubjectDescription();
            case GRADE -> updateGradeMark();
            case STUDENT -> updateStudentUsername();
            case GROUP -> updateGroupName();
            case EXIT -> exitApplication();
        }
    }

    private <U> void update(Service<?, U, ?> service, U dto) {
        if(dto == null) {
            navigateTo(getUpdateMenu());
        }
        Command updateCommand = new UpdateCommand<>(service, dto);
        updateCommand.execute();
    }

    private void updateTeacherName() {
        UpdateTeacherDTO updateTeacherDTO = updateForm.inputTeacherForm();
        update(getContainer().getTeacherInstance(), updateTeacherDTO);
    }

    private void updateSubjectDescription() {
        UpdateSubjectDTO updateSubjectDTO = updateForm.inputSubjectForm();
        update(getContainer().getSubjectInstance(), updateSubjectDTO);
    }

    private void updateGradeMark() {
        UpdateGradeDTO updateGradeDTO = updateForm.inputGradeForm();
        update(getContainer().getGradeInstance(), updateGradeDTO);
    }

    private void updateStudentUsername() {
        UpdateStudentDTO updateStudentDTO = updateForm.inputStudentForm();
        update(getContainer().getStudentInstance(), updateStudentDTO);
    }

    private void updateGroupName() {
        UpdateGroupDTO updateGroupDTO = updateForm.inputGroupForm();
        update(getContainer().getGroupInstance(), updateGroupDTO);
    }
}
