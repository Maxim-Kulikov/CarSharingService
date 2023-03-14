package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dao.repository.car.CarDao;
import org.example.dao.repository.car.CarMarkDao;
import org.example.dao.repository.car.CarModelDao;
import org.example.dto.carDTO.CarDescriptionDto;
import org.example.dto.carDTO.CarPresentationDto;
import org.example.dto.carDTO.CarCreationDto;
import org.example.mapper.car.CarMapper;
import org.example.mapper.car.CarMarkMapper;
import org.example.mapper.car.CarModelMapper;
import org.example.model.Car;
import org.example.model.CarMark;
import org.example.model.CarModel;
import org.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@ComponentScan("org.example")
@AllArgsConstructor
public class CarServiceImpl implements CarService {
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

    @Transactional
    @Override
    public CarPresentationDto getCarPresentation(Integer id){
        return carMapper.toCarPresentationDto(
                carDao.findFirstById(id).get()
        );
    }

    @Transactional
    @Override
    public CarDescriptionDto getCarDescription(CarPresentationDto dto){
        Optional<Car> car = carDao.findFirstById(dto.getId());
        return carMapper.toCarDescriptionDto(car.get());
    }

    @Override
    public Integer update(CarDescriptionDto dto) {
        String mark = dto.getMark(),
                model = dto.getModel();
        CarModel carModel = ifNotExistsSaveElseGetExisted(mark, model);
        Car car = carMapper.toCar(dto, carModel);

        carDao.save(car);
        return car.getId();
    }

    @Transactional
    @Override
    public CarDescriptionDto getCarDescription(Integer id){
        Optional<Car> car = carDao.findFirstById(id);
        return carMapper.toCarDescriptionDto(car.get());
    }

    @Transactional
    @Override
    public List<CarPresentationDto> getAllCarsPresentation(){
        return carMapper.toListCarPresentation(
                (List<Car>) carDao.findAll()
        );
    }

    @Transactional
    @Override
    public List<CarPresentationDto> getAllCarsPresentationByMark(String mark){
        return Stream.of(carDao.findAllByCarModel_Mark_Mark(mark))
                .map(car -> carMapper.toCarPresentationDto((Car) car))
                .toList();
    }

    @Transactional
    @Override
    public List<CarPresentationDto> getAllCarsPresentationByModel(String model){
        return Stream.of(carDao.findAllByCarModel_Model(model))
                .map(car -> carMapper.toCarPresentationDto((Car) car))
                .toList();
    }

    @Transactional
    @Override
    public List<CarPresentationDto> getAllCarsPresentationByMarkAndModel(String mark, String model){
        return carMapper.toListCarPresentation(
                carDao.findAllByCarModel_ModelAndCarModel_Mark_Mark(model, mark)
        );
    }

    @Override
    public List<CarDescriptionDto> getAllCarsDescription() {
        return carMapper.toListCarDescription((List<Car>) carDao.findAll());
    }

    @Transactional
    @Override
    public Integer save(CarCreationDto dto){
        String mark = dto.getMark(),
                model = dto.getModel();
        CarModel carModel = ifNotExistsSaveElseGetExisted(mark, model);
        Car car = carMapper.toCar(dto, carModel);

        carDao.save(car);
        return car.getId();
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        carDao.deleteById(id);
    }

    private CarModel ifNotExistsSaveElseGetExisted(String mark, String model){
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
        return carModel;
    }
}
