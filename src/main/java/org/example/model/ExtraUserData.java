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

    public Changer changer(){
        return new Changer();
    }
    public class Changer{
        public Changer id(Long id){
            ExtraUserData.this.id = id;
            return this;
        }
        public Changer passportNumber(String passportNumber){
            ExtraUserData.this.passportNumber = passportNumber;
            return this;
        }
        public Changer name(String name){
            ExtraUserData.this.name = name;
            return this;
        }
        public Changer lastname(String lastname){
            ExtraUserData.this.lastname = lastname;
            return this;
        }
        public Changer birthdate(Date birthdate){
            ExtraUserData.this.birthdate = birthdate;
            return this;
        }
        public Changer drivingLicense(String drivingLicense){
            ExtraUserData.this.drivingLicense = drivingLicense;
            return this;
        }
        public Changer phone(String phone){
            ExtraUserData.this.phone = phone;
            return this;
        }
        public Changer registerDate(Date registerDate){
            ExtraUserData.this.registerDate = registerDate;
            return this;
        }
        public ExtraUserData change(){
            return ExtraUserData.this;
        }
    }
}
