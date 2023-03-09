package org.example.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "users_id_seq")
    @GenericGenerator(name = "users_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToOne//(cascade = CascadeType.)
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_extra_users_data", referencedColumnName = "id")
    private ExtraUserData extraUserData;

}
