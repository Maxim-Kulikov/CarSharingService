package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CreationException extends Exception{
    CreationException(String message){
        super(message);
        System.out.println("Exception in creation: " + message);
    }
}
