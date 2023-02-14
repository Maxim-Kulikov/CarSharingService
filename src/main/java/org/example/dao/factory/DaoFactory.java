package org.example.dao.factory;

import org.example.dao.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("org.example.dao")
public class DaoFactory {
    //INSTANCE;

    /*private final UserDaoImpl userDao = new UserDaoImpl();
    private final CarDaoImpl carDao = new CarDaoImpl();
    private final OrderDaoImpl orderDao = new OrderDaoImpl();
    private final ExtraUserDaoImpl extraUserDao = new ExtraUserDaoImpl();
    private final RoleDaoImpl roleDao = new RoleDaoImpl();
     */
    @Bean
    @Scope("singleton")
    public UserDaoImpl getUserDao() {
        return new UserDaoImpl();
    }
    @Bean
    @Scope("singleton")
    public ExtraUserDaoImpl getExtraUserDaoImpl(){
        return new ExtraUserDaoImpl();
    }
    @Bean
    @Scope("singleton")
    public CarDaoImpl getCarDao(){
        return new CarDaoImpl();
    }
    @Bean
    @Scope("singleton")
    public OrderDaoImpl getOrderDao(){
        return new OrderDaoImpl();
    }
    @Bean
    @Scope("singleton")
    public RoleDaoImpl getRoleDao(){return new RoleDaoImpl();}
}
