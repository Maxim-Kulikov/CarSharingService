package org.example.dto.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Short id) {
        super("Role with id=" + id + " was not found");
    }
}
