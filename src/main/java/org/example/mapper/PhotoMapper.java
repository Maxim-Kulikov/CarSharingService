package org.example.mapper;

import org.example.dto.PhotoResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    @Mapping(target = "id", ignore = true)
    PhotoResp toPhotoResp(Integer idCar, MultipartFile file) throws IOException;
}
