package learn.field_agent.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(
                "HttpMessageNotReadableException",
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(DataAccessException ex) {
        return new ResponseEntity<>(
                "Something terrible happened, sorry :(",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception ex){
        return new ResponseEntity<>(
                "Something terrible happened, sorry :(",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
