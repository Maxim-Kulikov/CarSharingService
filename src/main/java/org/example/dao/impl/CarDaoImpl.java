package org.example.dao.impl;

import org.example.dao.CarDao;
import org.example.models.CarEntity;

import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao<CarEntity> {
    @Override
    public Optional<CarEntity> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<CarEntity> getAll() {
        return null;
    }

    @Override
    public void save(CarEntity car) {

    }

    @Override
    public void update(CarEntity car, String[] params) {

    }

    @Override
    public void delete(CarEntity car) {

    }

}
