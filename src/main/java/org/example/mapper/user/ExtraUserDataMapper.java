package org.example.mapper.user;

import org.example.dto.userDTO.ExtraUserDataResp;
import org.example.model.ExtraUserData;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ExtraUserDataMapper {
    ExtraUserDataResp toExtraUserDataResponse(ExtraUserData extraUserData);
    ExtraUserData toExtraUserData(ExtraUserDataResp extraUserDataResp);
    List<ExtraUserDataResp> toExtraUserDataResponseList(List<ExtraUserData> extraUserData);


}
