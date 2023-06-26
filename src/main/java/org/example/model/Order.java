package org.example.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToOne
    @JoinColumn(name = "id_admin", referencedColumnName = "id")
    private User admin;

    @Column(name = "refuse_reason")
    private String refuseReason;

    @Column(name = "price")
    private Long price;

    public Order.Changer changer() {
        return new Changer();
    }

    public class Changer {

        private Changer() {
        }

        public Order.Changer startDate(java.util.Date startDate) {
            if (!Order.this.startDate.equals(startDate) && startDate != null) {
                Order.this.startDate = startDate;
            }
            return this;
        }

        public Order.Changer finishDate(java.util.Date finishDate) {
            if (!Order.this.finishDate.equals(finishDate) && finishDate != null) {
                Order.this.finishDate = finishDate;
            }
            return this;
        }

        public Order.Changer status(Boolean status) {
            if (!Order.this.status.equals(status) && status != null) {
                Order.this.status = status;
            }
            return this;
        }

        public Order.Changer car(Car car) {
            if (!Order.this.car.equals(car) && car != null) {
                Order.this.car = car;
            }
            return this;
        }

        public Order.Changer user(User user) {
            if (!Order.this.user.equals(user) && user != null) {
                Order.this.user = user;
            }
            return this;
        }

        public Order.Changer admin(User admin) {
            if (!Order.this.admin.equals(admin) && admin != null) {
                Order.this.admin = admin;
            }
            return this;
        }

        public Order.Changer refuseReason(String refuseReason) {
            if (!Order.this.refuseReason.equals(refuseReason) && refuseReason != null) {
                Order.this.refuseReason = refuseReason;
            }
            return this;
        }

        public Order.Changer price(Long price) {
            if (!Order.this.price.equals(price) && price != null) {
                Order.this.price = price;
            }
            return this;
        }

        public Order change() {
            return Order.this;
        }
    }

}
