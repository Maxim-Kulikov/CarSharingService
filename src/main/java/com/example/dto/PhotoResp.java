package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoResp implements Serializable {
    @JsonProperty
    private byte[]bytes;
    @JsonProperty
    private String name;
    @JsonProperty
    private String id;
    @JsonProperty
    private Integer idCar;
}
