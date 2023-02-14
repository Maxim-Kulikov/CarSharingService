package org.example.dao.impl;

import org.example.model.CarModel;
import org.example.dao.Dao;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Component
public class CarModelDaoImpl implements Dao<CarModel> {
    @Override
    public Optional<CarModel> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<CarModel> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(CarModel carModel) {

    }

    @Override
    public void update(CarModel carModel, String[] params) {

    }

    @Override
    public void delete(CarModel carModel) {

    }
}
