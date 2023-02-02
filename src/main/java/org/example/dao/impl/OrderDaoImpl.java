package org.example.dao.impl;

import org.example.dao.OrderDao;
import org.example.models.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao<Order> {

    @Override
    public Optional<Order> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public void update(Order order, String[] params) {

    }

    @Override
    public void delete(Order order) {

    }

}
