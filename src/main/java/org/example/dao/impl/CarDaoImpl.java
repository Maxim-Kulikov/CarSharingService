package org.example.dao.impl;

import org.example.dao.CarDao;
import org.example.models.Car;

import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao<Car> {
    @Override
    public Optional<Car> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Car> getAll() {
        return null;
    }

    @Override
    public void save(Car car) {

    }

    @Override
    public void update(Car car, String[] params) {

    }

    @Override
    public void delete(Car car) {

    }

}
