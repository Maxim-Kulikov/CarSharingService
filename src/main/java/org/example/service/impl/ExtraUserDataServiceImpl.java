package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.example.dao.repository.ExtraUserDao;
import org.example.dto.userDTO.ExtraUserDataResponse;
import org.example.mapper.ExtraUserDataMapper;
import org.example.model.ExtraUserData;
import org.example.service.ExtraUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
@Service
@Transactional
@EqualsAndHashCode
@AllArgsConstructor
@ComponentScan("org.example")
public class ExtraUserDataServiceImpl implements ExtraUserDataService {
    @Autowired
    private final ExtraUserDao extraUserDao;
    @Autowired
    private final ExtraUserDataMapper extraUserDataMapper;

    @Override
    public List<ExtraUserDataResponse> getAll() {
        return extraUserDataMapper.toExtraUserDataResponseList((List<ExtraUserData>) extraUserDao.findAll());
    }

    @Override
    public Long update(ExtraUserDataResponse dto) {
        return extraUserDao.save(
                extraUserDataMapper.toExtraUserData(dto)
        ).getId();
    }

    @Override
    public ExtraUserDataResponse get(Long id) {
        return extraUserDataMapper.toExtraUserDataResponse(
                extraUserDao.findById(id).get()
        );
    }
}
