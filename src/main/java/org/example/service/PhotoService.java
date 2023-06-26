package org.example.service;

import org.example.dto.exception.CarNotFoundException;
import org.example.dto.exception.PhotoNotFoundException;
import org.example.dto.photoDTO.PhotoRespDto;
import org.example.dto.photoDTO.SavePhotoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface PhotoService {
    ResponseEntity<List<PhotoRespDto>> getAll(Integer idCar);

    void save(MultipartFile file, Integer idCar) throws IOException, CarNotFoundException;

    ResponseEntity<PhotoRespDto> get(String id);
}
