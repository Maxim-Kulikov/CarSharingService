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

    public Changer changer(){
        return new Changer();
    }

    public class Changer{
        public Changer id(Integer id){
            Car.this.id = id;
            return this;
        }
        public Changer carModel(CarModel carModel){
            Car.this.carModel = carModel;
            return this;
        }
        public Changer carNumber(String carNumber){
            Car.this.carNumber = carNumber;
            return this;
        }
        public Changer price(Integer price){
            Car.this.price = price;
            return this;
        }
        public Changer limitations(String limitations){
            Car.this.limitations = limitations;
            return this;
        }
        public Car change(){
            return Car.this;
        }
    }
}
