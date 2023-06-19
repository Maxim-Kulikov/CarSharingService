package org.example.dto.exception;

public class ModelNotFoundException extends Exception {
    public ModelNotFoundException(String model){
        super("Model " + model + " was not found");
    }

    public ModelNotFoundException(String model, String mark){
        super("Model " + mark + " " + model + " was not found");
    }
}
