package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dao.repository.CarDao;
import org.example.dao.repository.CarMarkDao;
import org.example.dao.repository.CarModelDao;
import org.example.dto.carDTO.CarPresentationDto;
import org.example.dto.carDTO.CarCreationDto;
import org.example.mapper.CarMapper;
import org.example.mapper.CarMarkMapper;
import org.example.mapper.CarModelMapper;
import org.example.model.Car;
import org.example.model.CarMark;
import org.example.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@ComponentScan("org.example")
@AllArgsConstructor
public class CarServiceImpl {
    @Autowired
    private final CarMapper carMapper;
    @Autowired
    private final CarMarkMapper carMarkMapper;
    @Autowired
    private final CarModelMapper carModelMapper;
    @Autowired
    private final CarDao carDao;
    @Autowired
    private final CarMarkDao carMarkDao;
    @Autowired
    private final CarModelDao carModelDao;

    public CarPresentationDto getCarPresentationById(int id){
        return carMapper.toCarPresentationDto(
                carDao.findFirstById(id).get()
        );
    }

    public CarPresentationDto getCarPresentationByMarkAndModel(String mark, String model){
        return carMapper.toCarPresentationDto(
                carDao.findFirstByCarModel_Mark_MarkAndCarModel_Model(mark, model).get()
        );
    }

    public CarPresentationDto getCarPresentationByMark(String mark){
        return carMapper.toCarPresentationDto(
                carDao.findFirstByCarModel_Mark_Mark(mark).get()
        );
    }

    public CarPresentationDto getCarPresentationByModel(String model){
        return carMapper.toCarPresentationDto(
                carDao.findFirstByCarModel_Model(model).get()
        );
    }

    public List<CarPresentationDto> getAllCarsPresentation(){
        return Stream.of(carDao.findAll())
                .map(car -> carMapper.toCarPresentationDto((Car) car))
                .toList();
    }

    @Transactional
    public List<CarPresentationDto> getAllCarsPresentationByMark(String mark){
        return Stream.of(carDao.findAllByCarModel_Mark_Mark(mark))
                .map(car -> carMapper.toCarPresentationDto((Car) car))
                .toList();
    }

    @Transactional
    public List<CarPresentationDto> getAllCarsPresentationByModel(String model){
        return Stream.of(carDao.findAllByCarModel_Model(model))
                .map(car -> carMapper.toCarPresentationDto((Car) car))
                .toList();
    }

    @Transactional
    public List<CarPresentationDto> getAllCarsPresentationByMarkAndModel(String mark, String model){
        return Stream.of(carDao.findAllByCarModel_ModelAndCarModel_Mark_Mark(mark, model))
                .map(car -> carMapper.toCarPresentationDto((Car) car))
                .toList();
    }

    @Transactional
    public Integer save(CarCreationDto dto){
        String mark = dto.getMark(),
                model = dto.getModel();
        CarMark carMark = null;
        CarModel carModel = null;

        if(!carModelDao.existsCarModelByModelAndMark_Mark(model, mark)){
            carMark = carMarkDao.existsCarMarkByMark(mark)
                    ? carMarkDao.findCarMarkByMark(mark).get() : carMarkDao.save(carMarkMapper.toCarMark(mark));
            carModel = carModelMapper.toCarModel(model, carMark);
            carModelDao.save(carModel);
        } else {
            carModel = carModelDao.findCarModelByModelAndMark_Mark(model, mark).get();
        }

        Car car = carMapper.toCar(dto, carModel);
        carDao.save(car);
        return car.getId();
    }
}
