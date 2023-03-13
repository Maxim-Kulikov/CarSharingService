package org.example.mapper;

import org.example.dto.carDTO.CarPresentationDto;
import org.example.dto.carDTO.CarCreationDto;
import org.example.model.Car;
import org.example.model.CarModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;

import javax.validation.constraints.Null;

import static org.mapstruct.MappingConstants.NULL;

@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(target = "idImage", source = "idImage")
    @Mapping(target = "mark", source = "carModel.mark.mark")
    @Mapping(target = "model", source = "carModel.model")
    public CarPresentationDto toCarPresentationDto(Car car);

    //TODO need or not param CarModel
    @Mapping(target = "carNumber", source = "dto.number")
    @Mapping(target = "carModel", source = "carModel")
    @Mapping(target = "id", ignore = true)
    public Car toCar(CarCreationDto dto, CarModel carModel);
}
