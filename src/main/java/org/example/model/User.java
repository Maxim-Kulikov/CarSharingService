package org.example.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "users_id_seq")
    @GenericGenerator(name = "users_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_extra_users_data", referencedColumnName = "id")
    private ExtraUserData extraUserData;

    public Changer changer() {
        return new Changer();
    }

    public class Changer {

        public Changer login(String login) {
            if (!login.isBlank()) {
                User.this.login = login;
            }
            return this;
        }

        public Changer password(String password) {
            if (!password.isBlank()) {
                User.this.password = password;
            }
            return this;
        }

        public Changer role(Role role) {
            User.this.role = role;
            return this;
        }

        public Changer extraUserData(ExtraUserData extraUserData) {
            User.this.extraUserData = extraUserData;
            return this;
        }

        public User change() {
            return User.this;
        }

    }
}
