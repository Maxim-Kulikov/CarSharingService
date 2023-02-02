package org.example.dao.impl;

import org.example.dao.ExtraUserDao;
import org.example.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ExtraUserDaoImpl implements ExtraUserDao<User> {
    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }
}
