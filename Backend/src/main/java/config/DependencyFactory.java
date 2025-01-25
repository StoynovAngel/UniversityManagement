package config;

import utils.handlers.*;
import services.*;

public class DependencyFactory {

    public FileHandler createFileHandler() {
        return new FileHandler("files/");
    }

    public FileService createFileService() {
        return new FileService(createFileHandler());
    }
}
