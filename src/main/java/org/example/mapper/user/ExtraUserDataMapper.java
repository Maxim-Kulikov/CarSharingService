package org.example.mapper.user;

import org.example.dto.userDTO.ExtraUserDataResponse;
import org.example.model.ExtraUserData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExtraUserDataMapper {
    ExtraUserDataResponse toExtraUserDataResponse(ExtraUserData extraUserData);
    ExtraUserData toExtraUserData(ExtraUserDataResponse extraUserDataResponse);
    List<ExtraUserDataResponse> toExtraUserDataResponseList(List<ExtraUserData> extraUserData);
}
