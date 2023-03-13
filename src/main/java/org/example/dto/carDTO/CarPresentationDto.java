package org.example.dto.carDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarPresentationDto {
    private String mark;
    private String model;
    private Integer idImage;
}
