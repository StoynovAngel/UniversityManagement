package com.angel.uni.management.command;

import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.Service;

public class CreateCommand<T, C> implements Command {

    private final Service<?, ?, C> service;
    private final C dto;

    public CreateCommand(Service<?, ?, C> service, C dto) {
        this.service = service;
        this.dto = dto;
    }

    @Override
    public void execute() {
        service.create(dto);
    }
}