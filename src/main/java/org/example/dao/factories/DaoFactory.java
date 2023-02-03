package org.example.dao.factories;

import org.example.dao.impl.CarDaoImpl;
import org.example.dao.impl.ExtraUserDaoImpl;
import org.example.dao.impl.OrderDaoImpl;
import org.example.dao.impl.UserDaoImpl;

public enum DaoFactory {
    INSTANCE;

    private final UserDaoImpl userDao = new UserDaoImpl();
    private final CarDaoImpl carDao = new CarDaoImpl();
    private final OrderDaoImpl orderDao = new OrderDaoImpl();
    private final ExtraUserDaoImpl extraUserDao = new ExtraUserDaoImpl();

    public UserDaoImpl getUserDao() {
        return userDao;
    }
    public ExtraUserDaoImpl getExtraUserDaoImpl(){
        return extraUserDao;
    }
    public CarDaoImpl getCarDao(){
        return carDao;
    }

    public OrderDaoImpl getOrderDao(){
        return orderDao;
    }
}
