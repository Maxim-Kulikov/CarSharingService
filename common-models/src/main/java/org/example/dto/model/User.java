package org.example.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String login;
    private String role;
    private Long idExtraData;

    private String passportNumber;
    private String name;
    private String lastname;
    private Date birthdate;
    private String drivingLicense;
    private String phone;
    private Date registerDate;

}
