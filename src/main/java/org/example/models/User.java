package org.example.models;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String login;
    private String password;
    private Integer role;
    private Long idExtraData;

    private String idPassport;
    private String name;
    private String lastname;
    private Date birthdate;
    private String drivingLicense;
    private String phone;
    private Date registerDate;
}
