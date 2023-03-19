package org.example.dto.carDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarCreateReq {
    private String mark;
    private String model;
    private Integer price;
    private String number;
    private Integer idImage;
    private String limitations;
}
