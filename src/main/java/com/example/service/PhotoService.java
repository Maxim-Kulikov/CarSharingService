package com.example.service;

import com.example.dto.PhotoResp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface PhotoService {
    @Transactional
    String save(Integer idCar, MultipartFile file);
    @Transactional
    String save(PhotoResp photoResp);
    @Transactional
    PhotoResp findById(String id);
    @Transactional
    PhotoResp findByCarId(Integer idCar);
    @Transactional
    List<PhotoResp> findAllByCarId(Integer idCar);
}
