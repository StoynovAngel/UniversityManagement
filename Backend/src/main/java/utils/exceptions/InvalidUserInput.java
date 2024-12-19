package utils.exceptions;

public class InvalidUserInput extends RuntimeException {
    public InvalidUserInput(String message) {
        super(message);
    }
}
