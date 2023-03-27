package com.example.photo_service.photo_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoResp {
    private byte[]bytes;
    private String name;
    private String id;
    private Integer idCar;
}
