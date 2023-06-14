package org.example.service;

import org.example.dto.photoDTO.PhotoRespDto;
import org.example.dto.photoDTO.SavePhotoDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface PhotoService {
    List<PhotoRespDto> getAll(Integer idCar);

    List<PhotoRespDto> getAll();

    void save(SavePhotoDto dto) throws IOException;

    PhotoRespDto get(String id);
}
