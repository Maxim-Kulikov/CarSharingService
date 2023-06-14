package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dto.carDTO.*;
import org.example.dto.sortenum.SortField;
import org.example.dto.sortenum.SortOrder;
import org.example.repository.car.CarDao;
import org.example.repository.car.CarMarkDao;
import org.example.repository.car.CarModelDao;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
    public CarInfoResp getCarPresentation(Integer id) {
        return carMapper.toCarPresentationDto(
                carDao.findFirstById(id).
                        orElseThrow(() -> new RuntimeException("Could not find car by this id!"))
        );
    }

    @Transactional
    @Override
    public CarDescriptionResp update(CarUpdateReq dto, Integer id) {
        Car car = carDao.findFirstById(id)
                .orElseThrow(() -> new RuntimeException("Could not find car by this id!"));
        CarModel carModel = saveOrGetExisted(dto.getMark(), dto.getModel());

        car = updateCarWithChanger(dto, car, carModel);
        carDao.save(car);

        return carMapper.toCarDescriptionResp(car);
    }

    @Transactional
    @Override
    public CarDescriptionResp getCarDescription(Integer id) {
        Car car = carDao.findFirstById(id)
                .orElseThrow(() -> new RuntimeException("Could not find car by this id!"));
        return carMapper.toCarDescriptionResp(car);
    }

    @Transactional
    @Override
    public List<CarInfoResp> getAllCarsPresentation(CarFilterReq filter) {
        return carMapper.toListCarPresentation(
                filterCars((List<Car>) carDao.findAll(), filter)
        );
    }

    @Transactional
    @Override
    public List<CarDescriptionResp> getAllCarsDescription(CarFilterReq filter) {
        return carMapper.toListCarDescription(
                filterCars((List<Car>) carDao.findAll(), filter)
        );
    }

    @Transactional
    @Override
    public CarDescriptionResp save(CarCreateReq dto) {
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

    private CarModel saveOrGetExisted(String mark, String model) {
        if (mark == null || model == null)
            return null;

        CarMark carMark = null;
        CarModel carModel = null;

        if (!carModelDao.existsCarModelByModelAndMark_Mark(model, mark)) {
            carMark = carMarkDao.existsCarMarkByMark(mark)
                    ? carMarkDao.findCarMarkByMark(mark).get() : carMarkDao.save(carMarkMapper.toCarMark(mark));
            carModel = carModelMapper.toCarModel(model, carMark);
            carModelDao.save(carModel);
        } else {
            carModel = carModelDao.findCarModelByModelAndMark_Mark(model, mark).get();
        }
        return carModel;
    }

    private List<Car> filterCars(List<Car> cars, CarFilterReq filter) {
        List<Integer> modelIds = filter.getIdModels();
        List<Integer> markIds = filter.getIdMarks();
        Integer minPrice = filter.getMinPrice();
        Integer maxPrice = filter.getMaxPrice();
        SortOrder sortOrder = filter.getSortOrder();
        SortField sortField = filter.getSortField();

        Stream<Car> stream = cars.stream().filter(car -> {
            if (!(modelIds == null
                    || modelIds.isEmpty()
                    || modelIds.contains(car.getCarModel().getId()))
            ) {
                return false;
            }

            if (!(markIds == null
                    || markIds.isEmpty()
                    || markIds.contains(car.getCarModel().getMark().getId()))
            ) {
                return false;
            }

            if (!(minPrice == null || car.getPrice() >= minPrice)) {
                return false;
            }

            if (!(maxPrice == null || car.getPrice() <= maxPrice)) {
                return false;
            }
            return true;
        });

        return sortField != null && sortOrder != null ? stream.sorted(new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                if (sortField == SortField.mark) {
                    if (sortOrder == SortOrder.asc) {
                        return compareMarks(o1, o2);
                    }
                    return compareMarks(o2, o1);
                }

                if (sortField == SortField.model) {
                    if (sortOrder == SortOrder.asc) {
                        return compareModels(o1, o2);
                    }
                    return compareModels(o2, o1);
                }

                if (sortOrder == SortOrder.asc) {
                    return comparePrices(o1, o2);
                }
                return comparePrices(o2, o1);
            }

            private int compareMarks(Car o1, Car o2) {
                return o1.getCarModel().getMark().getMark().compareTo(o2.getCarModel().getMark().getMark());
            }

            private int compareModels(Car o1, Car o2) {
                return o1.getCarModel().getModel().compareTo(o2.getCarModel().getModel());
            }

            private int comparePrices(Car o1, Car o2) {
                return o1.getPrice() - o2.getPrice();
            }
        }).collect(Collectors.toList())
                : stream.collect(Collectors.toList());
    }


    private Car updateCar(CarUpdateReq dto, Car car, CarModel model) {
        return Car.builder()
                .id(car.getId())
                .carModel(model == null
                        ? car.getCarModel() : model)
                .carNumber(dto.getCarNumber().equals(car.getCarNumber()) || dto.getCarNumber().isEmpty()
                        ? car.getCarNumber() : dto.getCarNumber())
                .limitations(dto.getLimitations().equals(car.getLimitations()) || dto.getLimitations().isEmpty()
                        ? car.getLimitations() : dto.getLimitations())
                .price(dto.getPrice().equals(car.getPrice()) || dto.getPrice() == null
                        ? car.getPrice() : dto.getPrice())
                .build();
    }

    private Car updateCarWithChanger(CarUpdateReq dto, Car car, CarModel model) {
        return car.changer()
                .id(car.getId())
                .carModel(model == null
                        ? car.getCarModel() : model)
                .carNumber(dto.getCarNumber().equals(car.getCarNumber()) || dto.getCarNumber().isEmpty()
                        ? car.getCarNumber() : dto.getCarNumber())
                .limitations(dto.getLimitations().equals(car.getLimitations()) || dto.getLimitations().isEmpty()
                        ? car.getLimitations() : dto.getLimitations())
                .price(dto.getPrice().equals(car.getPrice()) || dto.getPrice() == null
                        ? car.getPrice() : dto.getPrice())
                .change();
    }
}
