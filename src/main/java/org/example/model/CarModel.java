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
@Table(name = "cars_models")
public class CarModel {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "cars_models_id_seq")
    @GenericGenerator(name = "cars_models_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(name = "model")
    private String model;

    @ManyToOne
    @JoinColumn(name = "id_mark", referencedColumnName = "id")
    private CarMark mark;
}

