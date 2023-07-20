package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.comparator.CarComparator;
import org.example.dto.carDTO.*;
import org.example.dto.exception.CarNotFoundException;
import org.example.dto.exception.MarkNotFoundException;
import org.example.dto.exception.ModelNotFoundException;
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
import org.example.util.SortParamsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
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
    public CarInfoResp getCarPresentation(Integer id) throws CarNotFoundException {
        return carMapper.toCarPresentationDto(
                carDao.findFirstById(id).
                        orElseThrow(() -> new CarNotFoundException(id))
        );
    }

    @Transactional
    @Override
    public CarDescriptionResp update(CarUpdateReq dto, Integer id) throws MarkNotFoundException, ModelNotFoundException, CarNotFoundException {
        Car car = carDao.findFirstById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        CarModel carModel = saveOrGetExisted(dto.getMark(), dto.getModel());

        car = update(dto, car, carModel);
        carDao.save(car);

        return carMapper.toCarDescriptionResp(car);
    }

    @Transactional
    @Override
    public CarDescriptionResp getCarDescription(Integer id) throws CarNotFoundException {
        Car car = carDao.findFirstById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
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
    public CarDescriptionResp save(CarCreateReq dto) throws MarkNotFoundException, ModelNotFoundException {
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

    private CarModel saveOrGetExisted(String mark, String model) throws ModelNotFoundException, MarkNotFoundException {
        if (mark == null || model == null)
            return null;

        CarMark carMark;
        CarModel carModel;

        if (!carModelDao.existsCarModelByModelAndMark_Mark(model, mark)) {
            carMark = carMarkDao.existsCarMarkByMark(mark)
                    ? carMarkDao.findCarMarkByMark(mark)
                    .orElseThrow(() -> new MarkNotFoundException(mark)) : carMarkDao.save(carMarkMapper.toCarMark(mark));
            carModel = carModelMapper.toCarModel(model, carMark);
            carModelDao.save(carModel);
        } else {
            carModel = carModelDao.findCarModelByModelAndMark_Mark(model, mark)
                    .orElseThrow(() -> new ModelNotFoundException(model, mark));
        }
        return Optional.of(carModel).orElseThrow(() -> new ModelNotFoundException(model, mark));
    }

    private List<Car> filterCars(List<Car> cars, CarFilterReq filter) {
        FilterData filterData = throwExceptionIfFilterNotCorrectOrGetData(filter);
        SortOrder sortOrder = filterData.sortParams.getSortOrder();
        SortField sortField = filterData.sortParams.getSortField();
        Integer minPrice = filter.getMinPrice();
        Integer maxPrice = filterData.getMaxPrice();
        List<Integer> modelIds = filter.getIdModels();
        List<Integer> markIds = filter.getIdMarks();

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

        return sortField != null && sortOrder != null ?
                stream.sorted(new CarComparator(sortField, sortOrder)).collect(Collectors.toList())
                : stream.collect(Collectors.toList());
    }

    private FilterData throwExceptionIfFilterNotCorrectOrGetData(CarFilterReq filter) {
        Integer minPrice = filter.getMinPrice();
        Integer maxPrice = filter.getMaxPrice();
        String sortFieldStr = filter.getSortField();
        String sortOrderStr = filter.getSortOrder();
        String message = "";

        if ((maxPrice != null && maxPrice < 0) || (minPrice != null && minPrice < 0)) {
            message = "Minimal price and maximal price should have positive value\n";
        } else if (minPrice != null && maxPrice != null && maxPrice < minPrice) {
            message = "Minimal price should be more then maximal\n";
        }

        SortParamsValidator.Data sortParams = null;
        try {
            sortParams = SortParamsValidator
                    .throwExceptionIfIncorrectInputOrElseGetData(sortOrderStr, sortFieldStr);
        } catch (RuntimeException e) {
            message += e.getMessage();
        }

        if (!message.isEmpty()) {
            throw new RuntimeException(message);
        }

        return new FilterData(sortParams, minPrice, maxPrice);
    }

    private Car update(CarUpdateReq dto, Car car, CarModel model) {
        return car.changer()
                .carModel(model)
                .carNumber(dto.getCarNumber())
                .limitations(dto.getLimitations())
                .price(dto.getPrice())
                .change();
    }

    @AllArgsConstructor
    @Getter
    private static class FilterData {
        private SortParamsValidator.Data sortParams;
        private Integer minPrice;
        private Integer maxPrice;
    }
}
