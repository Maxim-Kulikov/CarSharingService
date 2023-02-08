package org.example.dao.impl;

import org.example.dao.Dao;
import org.example.models.CarMarkEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CarMarkDaoImpl implements Dao<CarMarkEntity> {
    @Override
    public Optional<CarMarkEntity> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<CarMarkEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(CarMarkEntity carMarkEntity) {

    }

    @Override
    public void update(CarMarkEntity carMarkEntity, String[] params) {

    }

    @Override
    public void delete(CarMarkEntity carMarkEntity) {

    }
}
