package org.example.mapper.car;

import org.example.dto.carDTO.CarDescriptionDto;
import org.example.dto.carDTO.CarPresentationDto;
import org.example.dto.carDTO.CarCreationDto;
import org.example.model.Car;
import org.example.model.CarModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;

import javax.validation.constraints.Null;

import java.util.List;

import static org.mapstruct.MappingConstants.NULL;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(target = "idImage", source = "idImage")
    @Mapping(target = "mark", source = "carModel.mark.mark")
    @Mapping(target = "model", source = "carModel.model")
    CarPresentationDto toCarPresentationDto(Car car);
    @Mapping(target = "carNumber", source = "dto.number")
    @Mapping(target = "carModel", source = "carModel")
    @Mapping(target = "id", ignore = true)
    Car toCar(CarCreationDto dto, CarModel carModel);
    @Mapping(target = "mark", source = "carModel.mark.mark")
    @Mapping(target = "model", source = "carModel.model")
    CarDescriptionDto toCarDescriptionDto(Car car);
    List<CarPresentationDto> toListCarPresentation(List<Car>  cars);
    List<CarDescriptionDto> toListCarDescription(List<Car> cars);

    @Mapping(target = "id", source = "dto.id")
    Car toCar(CarDescriptionDto dto, CarModel carModel);
}
