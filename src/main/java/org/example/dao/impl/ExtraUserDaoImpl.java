package org.example.dao.impl;

import org.example.model.ExtraUserData;
import org.example.dao.ExtraUserDao;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
@ComponentScan
public class ExtraUserDaoImpl implements ExtraUserDao<ExtraUserData> {
    @Override
    public Optional<ExtraUserData> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<ExtraUserData> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(ExtraUserData extraUserData) {

    }

    @Override
    public void update(ExtraUserData extraUserData, String[] params) {

    }

    @Override
    public void delete(ExtraUserData extraUserData) {

    }
}
