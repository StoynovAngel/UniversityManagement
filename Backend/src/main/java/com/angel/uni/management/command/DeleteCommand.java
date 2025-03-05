package com.angel.uni.management.command;

import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.Service;

public class DeleteCommand<T> implements Command {

    private final Service<?, ?, ?> service;
    private Long id;

    public DeleteCommand(Service<?, ?, ?> service, Long id) {
        this.service = service;
        this.id = id;
    }

    @Override
    public void execute() {
        service.delete(id);
    }
}
