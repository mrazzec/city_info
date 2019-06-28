package by.dorozhuk.Exception;

public class DoesntExistServiceException extends Exception {

    public DoesntExistServiceException() {
        super();
    }

    public DoesntExistServiceException(String message) {
        super(message);
    }

    public DoesntExistServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoesntExistServiceException(Throwable cause) {
        super(cause);
    }

    protected DoesntExistServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
