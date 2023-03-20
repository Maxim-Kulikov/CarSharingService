package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UpdateException extends Exception{
    UpdateException(String message){
        super(message);
        System.out.println("Exception in updating: " + message);
    }
}
