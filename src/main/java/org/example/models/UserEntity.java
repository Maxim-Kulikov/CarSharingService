package org.example.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToOne//(cascade = CascadeType.)
    @JoinColumn(name = "idRole", referencedColumnName = "id")
    private RoleEntity roleEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idExtraUsersData", referencedColumnName = "id")
    private ExtraUserDataEntity extraUserDataEntity;

    /*private String passportNumber;
    private String name;
    private String lastname;
    private Date birthdate;
    private String drivingLicense;
    private String phone;
    private Date registerDate;*/
}
