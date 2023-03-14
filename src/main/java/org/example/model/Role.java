package org.example.model;

import lombok.*;
import org.hibernate.mapping.Set;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "roles")
public class Role /*implements GrantedAuthority*/ {
    @Id
    @Column(name = "id")
    private Short id;

    @Column(name = "role")
    private String role;

    /*@Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return role;
    }*/
}
