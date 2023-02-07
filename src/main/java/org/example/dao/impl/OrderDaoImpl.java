package org.example.dao.impl;

import org.example.dao.OrderDao;
import org.example.models.OrderEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao<OrderEntity> {

    @Override
    public Optional<OrderEntity> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<OrderEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(OrderEntity orderEntity) {

    }

    @Override
    public void update(OrderEntity orderEntity, String[] params) {

    }

    @Override
    public void delete(OrderEntity orderEntity) {

    }

}
