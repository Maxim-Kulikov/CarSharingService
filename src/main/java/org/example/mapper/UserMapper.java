package org.example.mapper;

import org.example.dto.roleDTO.RoleResponse;
import org.example.dto.userDTO.UserAuthorizeRequest;
import org.example.dto.userDTO.UserExisted;
import org.example.model.Role;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserAuthorizeRequest dto);
    @Mapping(target = "role.role", source = "role")
    User toUser(UserExisted dto);

    @Mapping(target = "role", source = "role.role")
    UserExisted toUserExisted(User user);
    List<UserExisted> toUserExistedList(List<User> users);

}
