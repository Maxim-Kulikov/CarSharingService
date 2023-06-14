package org.example.controller.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super("User with id=" + id.toString() + " was not found");
    }

    public UserNotFoundException(String login) {
        super("User with login=" + login + " was not found");
    }

    public UserNotFoundException(String login, String password) {
        super("User with login=" + login + " and input password was not found");
    }
}
