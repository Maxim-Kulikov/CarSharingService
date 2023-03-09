package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dao.repository.CarDao;
import org.example.dto.CarPresentationDto;
import org.example.mapper.CarMapper;
import org.example.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@ComponentScan("org.example")
@AllArgsConstructor
public class CarServiceImpl {
    @Autowired
    private final CarListServiceImpl carListServiceImpl;
    @Autowired
    private final CarMapper carMapper;
    @Autowired
    private final CarDao carDao;

    public CarPresentationDto getCarPresentationById(int id){
        return carMapper.CarToCarPresentationDto(
                carDao.findFirstById(id).get()
        );
    }

    public CarPresentationDto getCarPresentationByMarkAndModel(String mark, String model){
        return carMapper.CarToCarPresentationDto(
                carDao.findFirstByCarModel_Mark_MarkAndCarModel_Model(mark, model).get()
        );
    }

    public CarPresentationDto getCarPresentationByMark(String mark){
        return carMapper.CarToCarPresentationDto(
                carDao.findFirstByCarModel_Mark_Mark(mark).get()
        );
    }

    public CarPresentationDto getCarPresentationByModel(String model){
        return carMapper.CarToCarPresentationDto(
                carDao.findFirstByCarModel_Model(model).get()
        );
    }

    public List<CarPresentationDto> getAllCarsPresentation(){
        return Stream.of(carDao.findAll())
                .map(car -> carMapper.CarToCarPresentationDto((Car) car))
                .toList();
    }

    public List<CarPresentationDto> getAllCarsPresentationByMark(String mark){
        return Stream.of(carDao.findAllByCarModel_Mark_Mark(mark))
                .map(car -> carMapper.CarToCarPresentationDto((Car) car))
                .toList();
    }

    public List<CarPresentationDto> getAllCarsPresentationByModel(String model){
        return Stream.of(carDao.findAllByCarModel_Model(model))
                .map(car -> carMapper.CarToCarPresentationDto((Car) car))
                .toList();
    }

    public List<CarPresentationDto> getAllCarsPresentationByMarkAndModel(String mark, String model){
        return Stream.of(carDao.findAllByCarModel_ModelAndCarModel_Mark_Mark(mark, model))
                .map(car -> carMapper.CarToCarPresentationDto((Car) car))
                .toList();
    }

    public void save(int idModel, String number, int price, String limitations, int idImage){
        carDao.save(Car.builder()
                .carModel(carListServiceImpl.findById(idModel))
                .price(price)
                .limitations(limitations)
                .idImage(idImage)
                .carNumber(number)
                .build());
    }

}
