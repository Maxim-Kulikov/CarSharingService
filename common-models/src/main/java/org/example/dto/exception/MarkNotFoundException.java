package org.example.dto.exception;

public class MarkNotFoundException extends Exception {
    public MarkNotFoundException(String mark){
        super("Mark " + mark + " was not found");
    }
}
