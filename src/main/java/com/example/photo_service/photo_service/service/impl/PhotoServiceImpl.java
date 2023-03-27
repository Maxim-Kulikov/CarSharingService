package com.example.photo_service.photo_service.service.impl;

import com.example.photo_service.photo_service.document.Photo;
import com.example.photo_service.photo_service.repository.PhotoRepository;
import com.example.photo_service.photo_service.File;
import com.example.photo_service.photo_service.dto.PhotoResp;
import com.example.photo_service.photo_service.mapper.PhotoMapper;
import com.example.photo_service.photo_service.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private final PhotoRepository photoRepository;
    @Autowired
    private final PhotoMapper photoMapper;

    @Transactional
    public String save(Integer idCar, MultipartFile file) {

        File filePhoto = null;
        try {
            filePhoto = File.builder()
                    .name(file.getOriginalFilename())
                    .bytes(file.getBytes())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Photo photo = Photo.builder()
                .idCar(idCar)
                .file(filePhoto)
                .build();

        photoRepository.insert(photo);
        return photo.getId();
    }

    @Transactional
    public PhotoResp findById(String id) {
        return photoMapper.toPhotoResp(
                photoRepository.findById(id).get());
    }

    @Transactional
    public PhotoResp findByCarId(Integer idCar){
        return photoMapper.toPhotoResp(
                photoRepository.findFirstByIdCar(idCar).get()
        );
    }

    @Transactional
    public List<PhotoResp> findAllByCarId(Integer idCar){
        return photoMapper.toPhotoRespList(
                photoRepository.findAllByIdCar(idCar)
        );
    }
}
