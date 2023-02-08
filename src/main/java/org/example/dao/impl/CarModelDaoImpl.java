package org.example.dao.impl;

import org.example.dao.Dao;
import org.example.models.CarModelEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CarModelDaoImpl implements Dao<CarModelEntity> {
    @Override
    public Optional<CarModelEntity> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<CarModelEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(CarModelEntity carModelEntity) {

    }

    @Override
    public void update(CarModelEntity carModelEntity, String[] params) {

    }

    @Override
    public void delete(CarModelEntity carModelEntity) {

    }
}
