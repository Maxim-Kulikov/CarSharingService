package org.example.mapper.car;

import org.example.model.CarMark;
import org.example.model.CarModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarModelMapper {
    @Mapping(target = "mark", source = "carMark")
    @Mapping(target = "id", ignore = true)
    CarModel toCarModel(String model, CarMark carMark);
}
