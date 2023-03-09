package org.example.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "extra_users_data")
public class ExtraUserData {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "extra_users_data_id_seq")
    @GenericGenerator(name = "extra_users_data_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Long id;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "driving_license")
    private String drivingLicense;

    @Column(name = "phone")
    private String phone;

    @Column(name = "register_date")
    private Date registerDate;
}
