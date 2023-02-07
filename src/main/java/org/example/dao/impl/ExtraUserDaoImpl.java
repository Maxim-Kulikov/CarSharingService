package org.example.dao.impl;

import org.example.dao.ExtraUserDao;
import org.example.models.UserEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExtraUserDaoImpl implements ExtraUserDao<UserEntity> {
    @Override
    public Optional<UserEntity> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<UserEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(UserEntity userEntity) {

    }

    @Override
    public void update(UserEntity userEntity, String[] params) {

    }

    @Override
    public void delete(UserEntity userEntity) {

    }
}
