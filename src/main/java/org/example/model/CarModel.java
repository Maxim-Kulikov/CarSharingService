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
@Table(name = "carsModels")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "model")
    private String model;

    @ManyToOne
    @JoinColumn(name = "idMark", referencedColumnName = "id")
    private CarMark mark;
}

