package org.example.mapper;

import org.example.dto.RegisterUserDto;
import org.example.model.User;
import org.springframework.stereotype.Component;
public interface UserMapperI {
    User registerUserDtoToModel(RegisterUserDto userDto);
}
