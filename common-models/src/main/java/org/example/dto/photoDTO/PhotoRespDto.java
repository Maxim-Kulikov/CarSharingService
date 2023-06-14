package org.example.dto.photoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoRespDto implements Serializable {
    private byte[] bytes;
    private String name;
    private String id;
    private Integer idCar;
}
