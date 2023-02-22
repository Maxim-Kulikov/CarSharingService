package org.example.mapper;

import org.example.dto.RegisterUserDto;
import org.example.model.User;
import org.springframework.stereotype.Component;

@Component

public class UserMapper {
    public User registerUserDtoToUserModel(RegisterUserDto userDto){
        return User.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .build();
    }
}
