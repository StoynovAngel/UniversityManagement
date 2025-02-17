package utils.exceptions;

public class DataRetrievalException extends RuntimeException {

    public DataRetrievalException(String message) {
        super(message);
    }

    public DataRetrievalException(String message, Throwable e) {
        super(message, e);
    }
}
