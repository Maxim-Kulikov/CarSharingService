package org.example.dto.exception;

public class UserIsExistedException extends Exception {
    public UserIsExistedException(String login) {
        super("User with login = " + login + " is already existed");
    }
}
