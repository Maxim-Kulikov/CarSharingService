package org.example.dao.impl;

import org.example.dao.ExtraUserDao;
import org.example.models.ExtraUserDataEntity;
import org.example.models.UserEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExtraUserDaoImpl implements ExtraUserDao<ExtraUserDataEntity> {
    @Override
    public Optional<ExtraUserDataEntity> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<ExtraUserDataEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(ExtraUserDataEntity extraUserDataEntity) {

    }

    @Override
    public void update(ExtraUserDataEntity extraUserDataEntity, String[] params) {

    }

    @Override
    public void delete(ExtraUserDataEntity extraUserDataEntity) {

    }
}
