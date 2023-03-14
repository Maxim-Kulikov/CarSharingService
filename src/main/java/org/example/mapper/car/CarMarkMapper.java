package org.example.mapper.car;

import org.example.model.CarMark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMarkMapper {
    CarMark toCarMark(String mark);
}
