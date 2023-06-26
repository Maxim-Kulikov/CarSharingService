package org.example.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Changer changer() {
        return new Changer();
    }

    public class Changer {

        private Changer() {
        }

        public Car.Changer carModel(CarModel carModel) {
            if (carModel != null
                    && carModel.getModel() != null
                    && !carModel.getModel().isBlank()
                    && carModel.getMark() != null
                    && !carModel.getMark().getMark().isBlank()) {
                Car.this.carModel = carModel;
            }
            return this;
        }

        public Car.Changer carNumber(String carNumber) {
            if (!Car.this.carNumber.equals(carNumber) && carNumber != null) {
                Car.this.carNumber = carNumber;
            }
            return this;
        }

        public Car.Changer price(Integer price) {
            if (!Car.this.price.equals(price) && price != null) {
                Car.this.price = price;
            }
            return this;
        }

        public Car.Changer limitations(String limitations) {
            if (!Car.this.limitations.equals(limitations) && limitations != null) {
                Car.this.limitations = limitations;
            }
            return this;
        }

        public Car change() {
            return Car.this;
        }
    }

}
