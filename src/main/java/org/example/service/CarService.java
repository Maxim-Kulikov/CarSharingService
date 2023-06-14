package org.example.service;

import org.example.dto.carDTO.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarService {
    CarInfoResp getCarPresentation(Integer id);
    List<CarInfoResp> getAllCarsPresentation(CarFilterReq filter);
    List<CarDescriptionResp> getAllCarsDescription(CarFilterReq filter);
    CarDescriptionResp getCarDescription(Integer id);
    CarDescriptionResp update(CarUpdateReq dto, Integer id);
    CarDescriptionResp save(CarCreateReq dto);
    void delete(Integer id);
}
