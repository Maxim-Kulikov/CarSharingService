package org.example.model;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
