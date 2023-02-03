package org.example;

public enum RolesAttributes {
    ID("roles.id"),
    ROLE("roles.role");

    public String columnName;
    RolesAttributes(String columnName){
        this.columnName = columnName;
    }

}
