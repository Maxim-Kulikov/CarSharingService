package org.example.service;

import org.example.dto.carDTO.CarCreateReq;
import org.example.dto.carDTO.CarDescriptionResp;
import org.example.dto.carDTO.CarInfoResp;
import org.example.dto.carDTO.CarUpdateReq;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarService {
    @Transactional
    CarInfoResp getCarPresentation(Integer id);
    @Transactional
    List<CarInfoResp> getAllCarsPresentation();
    @Transactional
    List<CarInfoResp> getAllCarsPresentationByMark(String mark);
    @Transactional
    List<CarInfoResp> getAllCarsPresentationByModel(String model);
    @Transactional
    List<CarInfoResp> getAllCarsPresentationByMarkAndModel(String mark, String model);
    @Transactional
    List<CarDescriptionResp> getAllCarsDescription();
    @Transactional
    CarDescriptionResp getCarDescription(Integer id);
    @Transactional
    CarDescriptionResp getCarDescription(CarInfoResp dto);
    @Transactional
    CarDescriptionResp update(CarUpdateReq dto, Integer id);
    @Transactional
    CarDescriptionResp save(CarCreateReq dto);
    @Transactional
    void delete(Integer id);
}
