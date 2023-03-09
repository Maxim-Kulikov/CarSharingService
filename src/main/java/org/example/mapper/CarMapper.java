package org.example.mapper;

import org.example.dto.CarPresentationDto;
import org.example.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public CarPresentationDto CarToCarPresentationDto(Car car) {
        return CarPresentationDto.builder()
                .mark(car.getCarModel().getMark().getMark())
                .model(car.getCarModel().getModel())
                .idImage(car.getIdImage())
                .build();
    }
}
