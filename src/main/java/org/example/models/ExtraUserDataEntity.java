package org.example.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "extraUsersData")
public class ExtraUserDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "drivingLicense")
    private String drivingLicense;

    @Column(name = "phone")
    private String phone;

    @Column(name = "registerDate")
    private Date registerDate;
}
