package org.example;

public enum UsersAttributes {
    ID("users.id"),
    LOGIN("users.login"),
    PASSWORD("users.password"),
    ID_ROLE("users.idRole"),
    ID_EXTRA_DATA("users.idExtraUsersData");

    public String columnName;
    UsersAttributes(String columnName){
        this.columnName = columnName;
    }
}
