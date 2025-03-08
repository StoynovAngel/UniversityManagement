package com.angel.uni.management.command;

import com.angel.uni.management.interfaces.Command;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.utils.gson.GsonFormatter;

import java.util.Optional;

public class ReadCommand<T, P> implements Command {
    private final Service<T, ?, ?> service;
    private final P param;

    public ReadCommand(Service<T, ?, ?> service, P param) {
        this.service = service;
        this.param = param;
    }

    @Override
    public void execute() {
        if (param instanceof String) {
            Optional<T> result = service.read((String) param);
            printToConsole(result);
        } else if (param instanceof Long) {
            Optional<T> result = service.read((Long) param);
            printToConsole(result);
        } else {
            throw new IllegalStateException("Neither id nor name was provided.");
        }
    }

    private void printToConsole(Optional<T> result) {
        if (result.isPresent()) {
            GsonFormatter.printObjectToJson(result.get());
        } else {
            System.out.println("No result found.");
        }
    }
}
