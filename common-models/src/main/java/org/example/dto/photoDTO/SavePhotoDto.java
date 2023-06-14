package org.example.dto.photoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Setter
@Getter
public class SavePhotoDto {
    private Integer idCar;
    private MultipartFile file;
}
