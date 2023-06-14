package org.example.controller.exception;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(String roleName){
        super("Role with name=" + roleName + " was not found");
    }
}
