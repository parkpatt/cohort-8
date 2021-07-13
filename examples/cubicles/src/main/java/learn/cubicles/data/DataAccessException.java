package learn.cubicles.data;

public class DataAccessException extends Exception {

    public DataAccessException(String message){
        super(message);
    }

    public DataAccessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
