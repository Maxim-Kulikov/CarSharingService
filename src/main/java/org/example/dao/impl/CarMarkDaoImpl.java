package org.example.dao.impl;

import org.example.model.CarMark;
import org.example.dao.Dao;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class CarMarkDaoImpl implements Dao<CarMark> {
    @Override
    public Optional<CarMark> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<CarMark> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(CarMark carMark) {

    }

    @Override
    public void update(CarMark carMark, String[] params) {

    }

    @Override
    public void delete(CarMark carMark) {

    }
}
