package org.example.model;

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
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "idModel", referencedColumnName = "id")
    private CarModel carModel;

    @Column(name = "carNumber")
    private String carNumber;

    @Column(name = "price")
    private Integer price;

    @Column(name = "limitations")
    private String limitations;

    @Column(name = "idImage")
    private Integer idImage;
}
