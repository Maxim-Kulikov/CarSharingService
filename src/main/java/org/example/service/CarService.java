package org.example.service;

import org.example.dto.carDTO.CarCreationDto;
import org.example.dto.carDTO.CarDescriptionDto;
import org.example.dto.carDTO.CarPresentationDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CarService {
    @Transactional
    CarPresentationDto getCarPresentation(Integer id);
    @Transactional
    List<CarPresentationDto> getAllCarsPresentation();
    @Transactional
    List<CarPresentationDto> getAllCarsPresentationByMark(String mark);
    @Transactional
    List<CarPresentationDto> getAllCarsPresentationByModel(String model);
    @Transactional
    List<CarPresentationDto> getAllCarsPresentationByMarkAndModel(String mark, String model);
    @Transactional
    List<CarDescriptionDto> getAllCarsDescription();
    @Transactional
    CarDescriptionDto getCarDescription(Integer id);
    @Transactional
    CarDescriptionDto getCarDescription(CarPresentationDto dto);
    @Transactional
    Integer update(CarDescriptionDto dto);
    @Transactional
    Integer save(CarCreationDto dto);
    @Transactional
    void delete(Integer id);
}
