package org.example.dao.impl;

import org.example.model.Car;
import org.example.dao.CarDao;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@ComponentScan
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
