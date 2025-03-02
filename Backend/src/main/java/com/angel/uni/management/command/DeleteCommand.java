package com.angel.uni.management.command;

import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.services.SubjectService;

public class DeleteCommand<T> implements Command {

    private T service;
    private Long id;

    public DeleteCommand(T service, Long id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public void execute() {
        deleteSubject();
    }

    private void deleteSubject() {
        if (!checkIfSubjectServiceType()) {
            throw new ClassCastException("Incorrect casting of " + service);
        }
            ((SubjectService) service).deleteSubject(id);
    }

    private boolean checkIfSubjectServiceType() {
        return service instanceof SubjectService;
    }
}
