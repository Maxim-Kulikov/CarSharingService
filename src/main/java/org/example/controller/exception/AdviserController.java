package org.example.controller.exception;

import org.example.dto.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AdviserController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handleUserNotFoundException(UserNotFoundException e) {
        return getResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Response> handleOrderNotFoundException(OrderNotFoundException e) {
        return getResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<Response> handleCarNotFoundException(CarNotFoundException e) {
        return getResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Response> handleCarNotFoundException(RoleNotFoundException e) {
        return getResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhotoNotFoundException.class)
    public ResponseEntity<Response> handleCarNotFoundException(PhotoNotFoundException e) {
        return getResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> handleRuntimeException(RuntimeException e) {
        return getResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MarkNotFoundException.class)
    public ResponseEntity<Response> handleMarkNotFoundException(MarkNotFoundException e) {
        return getResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<Response> handleModelNotFoundException(ModelNotFoundException e) {
        return getResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserIsNotAdminException.class)
    public ResponseEntity<Response> handleUserIsNotAdminException(UserIsNotAdminException e) {
        return getResponseEntity(e, HttpStatus.OK);
    }

    @ExceptionHandler(UserIsExistedException.class)
    public ResponseEntity<Response> handleUserIsExistedException(UserIsExistedException e) {
        return getResponseEntity(e, HttpStatus.OK);
    }

    private ResponseEntity<Response> getResponseEntity(Exception e, HttpStatus httpStatus) {
        Response response = new Response();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }

}
