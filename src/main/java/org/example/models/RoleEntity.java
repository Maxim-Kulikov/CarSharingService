package org.example.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @Column(name = "id")
    private Short id;

    @Column(name = "role")
    private String role;
}
