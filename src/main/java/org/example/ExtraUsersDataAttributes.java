package org.example;

public enum ExtraUsersDataAttributes {
    ID("extraUsersData.id"),
    PASSPORT_NUMBER("extraUsersData.passport_number"),
    NAME("extraUsersData.name"),
    LASTNAME("extraUsersData.lastname"),
    BIRTHDATE("extraUsersData.birthdate"),
    DRIVING_LICENSE("extraUsersData.drivingLicense"),
    PHONE("extraUsersData.phone"),
    REGISTER_DATE("extraUsersData.registerDate");

    public String columnName;
    ExtraUsersDataAttributes(String columnName){
        this.columnName = columnName;
    }

}
