package org.example.Models;

import lombok.Data;

@Data
public class User {
    private long id;
    private String login;
    private String password;
    private int role;
    private String idExtraUserData;
}
