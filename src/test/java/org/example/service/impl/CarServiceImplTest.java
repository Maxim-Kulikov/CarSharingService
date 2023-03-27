package org.example.service.impl;

import org.example.dao.repository.car.CarDao;
import org.example.dao.repository.car.CarMarkDao;
import org.example.dao.repository.car.CarModelDao;
import org.example.dto.carDTO.CarDescriptionResp;
import org.example.dto.carDTO.CarInfoResp;
import org.example.dto.carDTO.CarUpdateReq;
import org.example.mapper.car.CarMapper;
import org.example.model.Car;
import org.example.model.CarMark;
import org.example.model.CarModel;
import org.example.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CarServiceImpl.class})
class CarServiceImplTest {
    private Car car = getCar();
    private CarInfoResp carInfoResp = getCarInfoResp(car);
    private CarDescriptionResp carDescriptionResp = getCarDescriptionResp(car);
    private CarUpdateReq carUpdateReq = getCarUpdateReq();
    @MockBean
    private CarMapper carMapper;
    @MockBean
    private CarDao carDao;
    @MockBean
    private CarMarkDao carMarkDao;
    @MockBean
    private CarModelDao carModelDao;
    @Autowired
    CarService carService;
    private Integer id = 1;
    private Integer price = 1000;
    private String mark = "volvo";
    private String model = "xc-70";
    private Integer idImage = 1111;
    private String carNumber = "1234";
    private String limitations = "new";

    @Test
    void getCarPresentation() {
        when(carDao.findFirstById(id)).thenReturn(Optional.ofNullable(car));
        when(carMapper.toCarPresentationDto(car)).thenReturn(carInfoResp);


        CarInfoResp expected = carInfoResp;
        CarInfoResp result = carService.getCarPresentation(id);

        assertAll(()->{
           assertNotNull(result);
           assertEquals(result, expected);
        });
    }

    @Test
    void getAllCarsPresentation() {
        List<Car> cars = Arrays.asList(car, car);
        List<CarInfoResp> carsInfoResps = Arrays.asList(carInfoResp, carInfoResp);

        when(carMapper.toListCarPresentation(cars)).thenReturn(carsInfoResps);
        when(carDao.findAll()).thenReturn(cars);

        List<CarInfoResp> expected = carsInfoResps;
        List<CarInfoResp> result = carService.getAllCarsPresentation();

        assertAll(()->{
            assertNotNull(result);
            assertEquals(result, expected);
        });
    }

    @Test
    void getAllCarsPresentationByMark() {
        List<Car> cars = Arrays.asList(car, car);
        List<CarInfoResp> carsInfoResps = Arrays.asList(carInfoResp, carInfoResp);

        when(carMapper.toListCarPresentation(cars)).thenReturn(carsInfoResps);
        when(carDao.findAllByCarModel_Mark_Mark(mark)).thenReturn(cars);

        List<CarInfoResp> expected = carsInfoResps;
        List<CarInfoResp> result = carService.getAllCarsPresentationByMark(mark);

        assertAll(()->{
            assertNotNull(result);
            assertEquals(result, expected);
        });
    }

    @Test
    void getAllCarsPresentationByModel() {
        List<Car> cars = Arrays.asList(car, car);
        List<CarInfoResp> carsInfoResps = Arrays.asList(carInfoResp, carInfoResp);

        when(carMapper.toListCarPresentation(cars)).thenReturn(carsInfoResps);
        when(carDao.findAllByCarModel_Model(model)).thenReturn(cars);

        List<CarInfoResp> expected = carsInfoResps;
        List<CarInfoResp> result = carService.getAllCarsPresentationByModel(model);

        assertAll(()->{
            assertNotNull(result);
            assertEquals(result, expected);
        });
    }

    @Test
    void getAllCarsPresentationByMarkAndModel() {
        List<Car> cars = Arrays.asList(car, car);
        List<CarInfoResp> carsInfoResps = Arrays.asList(carInfoResp, carInfoResp);

        when(carMapper.toListCarPresentation(cars)).thenReturn(carsInfoResps);
        when(carDao.findAllByCarModel_ModelAndCarModel_Mark_Mark(model, mark)).thenReturn(cars);

        List<CarInfoResp> expected = carsInfoResps;
        List<CarInfoResp> result = carService.getAllCarsPresentationByMarkAndModel(mark, model);

        assertAll(()->{
            assertNotNull(result);
            assertEquals(result, expected);
        });
    }

    @Test
    void getAllCarsDescription() {
        List<CarDescriptionResp> carDescriptionResps = Arrays.asList(carDescriptionResp, carDescriptionResp);
        List<Car> cars = Arrays.asList(car, car);

        when(carMapper.toListCarDescription(cars)).thenReturn(carDescriptionResps);
        when(carDao.findAll()).thenReturn(cars);

        List<CarDescriptionResp> expected = carDescriptionResps;
        List<CarDescriptionResp> result = carService.getAllCarsDescription();

        assertAll(()->{
            assertNotNull(result);
            assertEquals(result, expected);
        });
    }

    @Test
    void getCarDescription() {
        when(carDao.findFirstById(id)).thenReturn(Optional.ofNullable(car));
        when(carMapper.toCarDescriptionResp(car)).thenReturn(carDescriptionResp);

        CarDescriptionResp expected = carDescriptionResp;
        CarDescriptionResp result = carService.getCarDescription(id);

        assertAll(()->{
            assertNotNull(result);
            assertEquals(result, expected);
        });
    }

    @Test
    void update() {
        when(carDao.findFirstById(id)).thenReturn(Optional.ofNullable(car));
        when(carDao.save(car)).thenReturn(car);
        when(carMapper.toCarDescriptionResp(car)).thenReturn(carDescriptionResp);

        CarDescriptionResp expected = carDescriptionResp;
        CarDescriptionResp result = carService.update(carUpdateReq, id);

        assertAll(()->{
            assertNotNull(result);
            assertEquals(result, expected);
        });
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    private Car getCar(){
        return Car.builder()
                .id(id)
                .price(price)
                .carNumber(carNumber)
                .limitations(limitations)
                .carModel(getCarModel())
                .build();
    }

    private CarModel getCarModel(){
        return CarModel.builder()
                .id(id)
                .mark(getCarMark())
                .model(model)
                .build();
    }

    private CarMark getCarMark(){
        return CarMark.builder()
                .id(id)
                .mark(mark)
                .build();
    }

    private CarInfoResp getCarInfoResp(Car car){
        return CarInfoResp.builder()
                .id(id)
                .mark(mark)
                .model(model)
                .build();
    }

    private CarUpdateReq getCarUpdateReq(){
        return CarUpdateReq.builder()
                .carNumber(carNumber)
                .model(model)
                .mark(mark)
                .limitations(limitations)
                .price(price)
                .build();
    }

    private CarDescriptionResp getCarDescriptionResp(Car car){
        return CarDescriptionResp.builder()
                .id(id)
                .carNumber(carNumber)
                .model(model)
                .mark(mark)
                .limitations(limitations)
                .price(price)
                .build();
    }
}