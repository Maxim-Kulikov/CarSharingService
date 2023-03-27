package com.example.photo_service.photo_service.mapper;

import com.example.photo_service.photo_service.document.Photo;
import com.example.photo_service.photo_service.dto.PhotoResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhotoMapper {

    @Mapping(target = "bytes", source = "file.bytes")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "idCar", source = "idCar")
    @Mapping(target = "name", source = "file.name")
    PhotoResp toPhotoResp(Photo photo);

    List<PhotoResp> toPhotoRespList(List<Photo> photos);
}
