package learn.exceptions;

public class TooBigException extends RuntimeException {

    public TooBigException(String message) {
        super(message);
    }

}
