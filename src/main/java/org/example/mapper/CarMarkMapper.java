package org.example.mapper;

import org.example.model.CarMark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMarkMapper {
    CarMark toCarMark(String mark);
}
