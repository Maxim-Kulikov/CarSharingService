package org.example.dto.photoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SavePhotoDto {
    private Integer idCar;
    private byte[] bytes;
    private String name;
}
