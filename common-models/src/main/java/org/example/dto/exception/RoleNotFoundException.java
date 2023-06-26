package org.example.dto.exception;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(Short id) {
        super("Role with id=" + id + " was not found");
    }
}
