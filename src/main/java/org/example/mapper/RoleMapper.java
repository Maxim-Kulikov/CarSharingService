package org.example.mapper;
import org.example.dto.roleDTO.RoleResp;
import org.example.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResp toRoleResponse(Role role);
}

