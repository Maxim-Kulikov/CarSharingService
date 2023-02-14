package org.example.dao.impl;

import org.example.model.Order;
import org.example.dao.OrderDao;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
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
