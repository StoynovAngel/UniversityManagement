package utils.exceptions;

public class DataUpdateException extends RuntimeException {

    public DataUpdateException(String message)  {
        super(message);
    }

    public DataUpdateException(String message, Throwable e)  {
        super(message, e);
    }
}
