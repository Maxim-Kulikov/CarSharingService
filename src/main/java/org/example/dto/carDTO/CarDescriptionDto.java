package org.example.dto.carDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.CarModel;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDescriptionDto {
    private Integer id;
    private String mark;
    private String model;
    private String carNumber;
    private Integer price;
    private String limitations;
    private Integer idImage;
}
