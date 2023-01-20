package org.example.Models;

import lombok.Data;

import java.sql.Date;

@Data
public class ExtraUserData {
    private String idPassport;
    private String name;
    private String lastname;
    private Date birthdate;
    private String drivingLicense;
    private String phone;
    private Date registerDate;
}
