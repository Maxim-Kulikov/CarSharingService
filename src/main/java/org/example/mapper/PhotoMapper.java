package org.example.mapper;

import org.example.dto.photoDTO.PhotoRespDto;
import org.example.dto.photoDTO.SavePhotoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    @Mapping(target = "id", ignore = true)
    PhotoRespDto toPhotoResp(Integer idCar, MultipartFile file) throws IOException;

    @Mapping(target = "name", source = "file.originalFilename")
    SavePhotoDto toSavePhoto(Integer idCar, MultipartFile file) throws IOException;
}
