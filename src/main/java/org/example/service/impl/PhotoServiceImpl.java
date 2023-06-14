package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.example.dto.photoDTO.PhotoRespDto;
import org.example.dto.photoDTO.SavePhotoDto;
import org.example.feign.PhotoGetterClient;
import org.example.kafka.producer.PhotoProducer;
import org.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<PhotoRespDto> getAll(Integer idCar) {
        return photoGetterClient.getByCarId(idCar);
    }

    @Override
    public List<PhotoRespDto> getAll() {
        return photoGetterClient.getAll();
    }

    @Override
    public void save(SavePhotoDto dto) throws IOException {
        photoProducer.send(dto.getIdCar(), dto.getFile());
    }

    @Override
    public PhotoRespDto get(String id) {
        return photoGetterClient.getById(id);
    }


}
