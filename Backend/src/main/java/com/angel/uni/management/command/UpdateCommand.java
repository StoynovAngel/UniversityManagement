package com.angel.uni.management.command;

import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.Service;

public class UpdateCommand<T, U> implements Command {

    private final Service<?, U, ?> service;
    private final U dto;

    public UpdateCommand(Service<?, U, ?> service, U dto) {
        this.service = service;
        this.dto = dto;
    }

    @Override
    public void execute() {
        service.update(dto);
    }
}