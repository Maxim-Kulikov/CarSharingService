package org.example.model;

import lombok.*;
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

    /*@Override
    public String getAuthority() {
        return role;
    }*/
}
