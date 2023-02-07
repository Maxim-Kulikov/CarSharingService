package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carsMarks")
public class CarMarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "mark")
    private String mark;
}
