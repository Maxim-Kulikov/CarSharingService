package org.example.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public class Changer {

        private Changer() {
        }

        public ExtraUserData.Changer passportNumber(String passportNumber) {
            if (ExtraUserData.this.passportNumber == null) {
                ExtraUserData.this.passportNumber = passportNumber;
                return this;
            }
            if (!ExtraUserData.this.passportNumber.equals(passportNumber) && passportNumber != null) {
                ExtraUserData.this.passportNumber = passportNumber;
            }
            return this;
        }

        public ExtraUserData.Changer name(String name) {
            if (ExtraUserData.this.name == null) {
                ExtraUserData.this.name = name;
                return this;
            }
            if (!ExtraUserData.this.name.equals(name) && name != null) {
                ExtraUserData.this.name = name;
            }
            return this;
        }

        public ExtraUserData.Changer lastname(String lastname) {
            if (ExtraUserData.this.lastname == null) {
                ExtraUserData.this.lastname = lastname;
                return this;
            }
            if (!ExtraUserData.this.lastname.equals(lastname) && lastname != null) {
                ExtraUserData.this.lastname = lastname;
            }
            return this;
        }

        public ExtraUserData.Changer birthdate(Date birthdate) {
            if (ExtraUserData.this.birthdate == null) {
                ExtraUserData.this.birthdate = birthdate;
                return this;
            }
            if (!ExtraUserData.this.birthdate.equals(birthdate) && birthdate != null) {
                ExtraUserData.this.birthdate = birthdate;
            }
            return this;
        }

        public ExtraUserData.Changer drivingLicense(String drivingLicense) {
            if (ExtraUserData.this.drivingLicense == null) {
                ExtraUserData.this.drivingLicense = drivingLicense;
                return this;
            }
            if (!ExtraUserData.this.drivingLicense.equals(drivingLicense) && drivingLicense != null) {
                ExtraUserData.this.drivingLicense = drivingLicense;
            }
            return this;
        }

        public ExtraUserData.Changer registerDate(Date registerDate) {
            if (ExtraUserData.this.registerDate == null) {
                ExtraUserData.this.registerDate = registerDate;
                return this;
            }
            if (!ExtraUserData.this.registerDate.equals(registerDate) && registerDate != null) {
                ExtraUserData.this.registerDate = registerDate;
            }
            return this;
        }

        public ExtraUserData.Changer phone(String phone) {
            if (ExtraUserData.this.phone == null) {
                ExtraUserData.this.phone = phone;
                return this;
            }
            if (!ExtraUserData.this.phone.equals(phone) && phone != null) {
                ExtraUserData.this.phone = phone;
            }
            return this;
        }

        public ExtraUserData change() {
            return ExtraUserData.this;
        }
    }

}
