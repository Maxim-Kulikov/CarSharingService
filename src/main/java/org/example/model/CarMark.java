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
@Table(name = "cars_marks")
public class CarMark {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "cars_marks_id_seq")
    @GenericGenerator(name = "cars_marks_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Integer id;

    @Column(name = "mark")
    private String mark;
}
