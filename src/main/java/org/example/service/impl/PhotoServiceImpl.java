package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.example.dto.exception.PhotoNotFoundException;
import org.example.dto.exception.Response;
import org.example.dto.photoDTO.PhotoRespDto;
import org.example.dto.photoDTO.SavePhotoDto;
import org.example.feign.PhotoGetterClient;
import org.example.kafka.producer.PhotoProducer;
import org.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@EqualsAndHashCode
@ComponentScan("org.example")
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private final PhotoGetterClient photoGetterClient;
    @Autowired
    private final PhotoProducer photoProducer;

    @Override
    @Transactional
    public ResponseEntity<List<PhotoRespDto>> getAll(Integer idCar) {
        return photoGetterClient.getAll(idCar);
    }

    @Override
    @Transactional
    public void save(MultipartFile file, Integer idCar) throws IOException {
        photoProducer.send(idCar, file);
    }

    @Override
    @Transactional
    public ResponseEntity<PhotoRespDto> get(String id) {
        return photoGetterClient.get(id);
    }

}
