package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.example.dto.PhotoResp;
import org.example.feign.PhotoGetterClient;
import org.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@EqualsAndHashCode
@ComponentScan("org.example")
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private final PhotoGetterClient photoGetterClient;

    @Override
    @Transactional
    public List<PhotoResp> getByCarId(Integer idCar) {
        return photoGetterClient.getByCarId(idCar);
    }
}
