package org.example.service;

import org.example.dto.userDTO.ExtraUserDataFilterReq;
import org.example.dto.userDTO.ExtraUserDataResp;
import org.example.dto.userDTO.ExtraUserDataUpdateReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExtraUserDataService {
    List<ExtraUserDataResp> getAll(ExtraUserDataFilterReq filter);

    ExtraUserDataResp update(ExtraUserDataUpdateReq dto, Long id);

    ExtraUserDataResp get(Long id);

}
