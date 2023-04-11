package org.example.service;

import org.example.dto.PhotoResp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PhotoService {
    List<PhotoResp> getByCarId(Integer idCar);
}
