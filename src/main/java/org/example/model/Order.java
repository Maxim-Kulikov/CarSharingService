package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "finishDate")
    private Date finishDate;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "idCar", referencedColumnName = "id")
    private Car car;

    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User user;

    @Column(name = "adminLogin")
    private String adminLogin;

    @Column(name = "refuseReason")
    private String refuseReason;
}
