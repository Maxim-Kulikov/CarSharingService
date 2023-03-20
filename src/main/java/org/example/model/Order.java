package org.example.model;

import lombok.*;
import org.aspectj.weaver.ast.Or;
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

    public Changer changer(){
        return new Changer();
    }

    public class Changer{
        public Changer id(Long id){
            Order.this.id = id;
            return this;
        }
        public Changer startDate(Date date){
            Order.this.startDate = date;
            return this;
        }
        public Changer finishDate(Date date){
            Order.this.finishDate = date;
            return this;
        }
        public Changer status(Boolean status){
            Order.this.status = status;
            return this;
        }
        public Changer car(Car car){
            Order.this.car = car;
            return this;
        }
        public Changer user(User user){
            Order.this.user = user;
            return this;
        }
        public Changer adminLogin(String adminLogin){
            Order.this.adminLogin = adminLogin;
            return this;
        }
        public Changer refuseReason(String refuseReason){
            Order.this.refuseReason = refuseReason;
            return this;
        }
        public Changer price(Long price){
            Order.this.price = price;
            return this;
        }
        public Order change(){
            return Order.this;
        }


    }
}
