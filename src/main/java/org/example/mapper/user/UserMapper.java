package org.example.mapper.user;

import org.example.dto.userDTO.UserAuthorizeReq;
import org.example.dto.userDTO.UserExistedResp;
import org.example.model.Role;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserAuthorizeReq dto);
    @Mapping(target = "role", source = "role")
    @Mapping(target = "id", source = "dto.id")
    User toUser(UserExistedResp dto, Role role);
    @Mapping(target = "role", source = "role.role")
    UserExistedResp toUserExistedResp(User user);
    List<UserExistedResp> toUserExistedList(List<User> users);

}
