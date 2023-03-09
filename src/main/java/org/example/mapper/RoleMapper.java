package org.example.mapper;
import org.example.dto.roleDTO.RoleResponse;
import org.example.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toRoleResponse(Role role);
}

