package com.angel.uni.management.command;

import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.Service;

import java.util.Optional;

public class ReadCommand<T> implements Command {
    private final Service<T, ?, ?> service;
    private Long id;
    private String name;

    public ReadCommand(Service<T, ?, ?> service, Long id) {
        this.service = service;
        this.id = id;
        this.name = null;
    }

    public ReadCommand(Service<T, ?, ?> service, String name) {
        this.service = service;
        this.name = name;
        this.id = null;
    }

    @Override
    public void execute() {
        if (id != null) {
            Optional<T> result = service.read(id);
            result.ifPresent(System.out::println);
        } else if (name != null) {
            Optional<T> result = service.read(name);
            result.ifPresent(System.out::println);
        } else {
            throw new IllegalStateException("Neither id nor name was provided.");
        }
    }
}
