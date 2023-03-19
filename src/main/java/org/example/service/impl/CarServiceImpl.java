package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dao.repository.car.CarDao;
import org.example.dao.repository.car.CarMarkDao;
import org.example.dao.repository.car.CarModelDao;
import org.example.dto.carDTO.CarCreateReq;
import org.example.dto.carDTO.CarDescriptionResp;
import org.example.dto.carDTO.CarInfoResp;
import org.example.dto.carDTO.CarUpdateReq;
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
    public CarInfoResp getCarPresentation(Integer id){
        return carMapper.toCarPresentationDto(
                carDao.findFirstById(id).
                        orElseThrow(() -> new RuntimeException("Could not find car by this id!"))
        );
    }

    @Transactional
    @Override
    public CarDescriptionResp getCarDescription(CarInfoResp dto){
        Car car = carDao.findFirstById(dto.getId()).orElseThrow(() -> new RuntimeException("Could not find car by this dto!"));
        return carMapper.toCarDescriptionResp(car);
    }

    @Transactional
    @Override
    public CarDescriptionResp update(CarUpdateReq dto, Integer id) {
        Car car = carDao.findFirstById(id)
                .orElseThrow(() -> new RuntimeException("Could not find car by this id!"));
        CarModel carModel = saveOrGetExisted(dto.getMark(), dto.getModel());

        car = updateCar(dto, car, carModel);
        carDao.save(car);

        return carMapper.toCarDescriptionResp(car);
    }

    @Transactional
    @Override
    public CarDescriptionResp getCarDescription(Integer id){
        Car car = carDao.findFirstById(id)
                .orElseThrow(() -> new RuntimeException("Could not find car by this id!"));
        return carMapper.toCarDescriptionResp(car);
    }

    @Transactional
    @Override
    public List<CarInfoResp> getAllCarsPresentation(){
        return carMapper.toListCarPresentation(
                (List<Car>) carDao.findAll()
        );
    }

    @Transactional
    @Override
    public List<CarInfoResp> getAllCarsPresentationByMark(String mark){
        return Stream.of(carDao.findAllByCarModel_Mark_Mark(mark))
                .map(car -> carMapper.toCarPresentationDto((Car) car))
                .toList();
    }

    @Transactional
    @Override
    public List<CarInfoResp> getAllCarsPresentationByModel(String model){
        return Stream.of(carDao.findAllByCarModel_Model(model))
                .map(car -> carMapper.toCarPresentationDto((Car) car))
                .toList();
    }

    @Transactional
    @Override
    public List<CarInfoResp> getAllCarsPresentationByMarkAndModel(String mark, String model){
        return carMapper.toListCarPresentation(
                carDao.findAllByCarModel_ModelAndCarModel_Mark_Mark(model, mark)
        );
    }

    @Transactional
    @Override
    public List<CarDescriptionResp> getAllCarsDescription() {
        return carMapper.toListCarDescription((List<Car>) carDao.findAll());
    }

    @Transactional
    @Override
    public CarDescriptionResp save(CarCreateReq dto){
        String mark = dto.getMark(),
                model = dto.getModel();
        CarModel carModel = saveOrGetExisted(mark, model);
        Car car = carMapper.toCar(dto, carModel);

        carDao.save(car);
        return carMapper.toCarDescriptionResp(car);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        carDao.deleteById(id);
    }

    private CarModel saveOrGetExisted(String mark, String model){
        if(mark == null || model == null)
            return null;

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

    private Car updateCar(CarUpdateReq dto, Car car, CarModel model){
        return Car.builder()
                .id(car.getId())
                .carModel(model == null
                        ? car.getCarModel() : model)
                .carNumber(dto.getCarNumber().equals(car.getCarNumber()) || dto.getCarNumber().isEmpty()
                        ? car.getCarNumber() : dto.getCarNumber())
                .idImage(dto.getIdImage().equals(car.getIdImage()) || dto.getIdImage() == null
                        ? car.getIdImage() : dto.getIdImage())
                .limitations(dto.getLimitations().equals(car.getLimitations()) || dto.getLimitations().isEmpty()
                        ? car.getLimitations() : dto.getLimitations())
                .price(dto.getPrice().equals(car.getPrice()) || dto.getPrice() == null
                        ? car.getPrice() : dto.getPrice())
                .build();
    }
}
