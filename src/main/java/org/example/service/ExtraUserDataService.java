package org.example.service;

import org.example.dto.userDTO.ExtraUserDataResponse;
import org.example.dto.userDTO.UserExisted;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExtraUserDataService {
    List<ExtraUserDataResponse> getAll();
    Long update(ExtraUserDataResponse dto);
    ExtraUserDataResponse get(Long id);

}
