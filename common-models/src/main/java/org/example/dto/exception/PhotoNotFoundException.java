package org.example.dto.exception;

public class PhotoNotFoundException extends Exception {
    public PhotoNotFoundException(String id){
        super("Photo with id=" + id + " was not found");
    }
    public PhotoNotFoundException(Integer idCar){
        super("Photo with car id=" + idCar.toString() + " was not found");
    }
}
