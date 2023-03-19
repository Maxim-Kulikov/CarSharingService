package org.example.dto.carDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDescriptionResp {
    private Integer id;
    private String mark;
    private String model;
    private String carNumber;
    private Integer price;
    private String limitations;
    private Integer idImage;
}
