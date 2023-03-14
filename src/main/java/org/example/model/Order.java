package org.example.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(generator = "orders_id_seq")
    @GenericGenerator(name = "orders_id_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator")
    private Long id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "finish_date")
    private Date finishDate;

    @Column(name = "status")
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "id_car", referencedColumnName = "id")
    private Car car;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @Column(name = "admin_login")
    private String adminLogin;

    @Column(name = "refuse_reason")
    private String refuseReason;

    @Column(name = "price")
    private Long price;
}
