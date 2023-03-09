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
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "cars_id_seq")
    @GenericGenerator(name = "cars_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_model", referencedColumnName = "id")
    private CarModel carModel;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "price")
    private Integer price;

    @Column(name = "limitations")
    private String limitations;

    @Column(name = "id_image")
    private Integer idImage;
}
