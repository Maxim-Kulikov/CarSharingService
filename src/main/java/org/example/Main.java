package org.example;

import org.example.dao.factories.DaoFactory;
import org.example.dao.config.ConnectionPool;
import org.example.dao.impl.UserDaoImpl;
import org.example.models.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*List<User> users;
        try {
            users = DaoFactory.INSTANCE.getUserDao().getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ConnectionPool.INSTANCE.getPool().closePool();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        for(User user:users){
            System.out.println(user.getLogin() + " " + user.getPassword());
        }*/
        //System.out.println(UserDaoImp.UsersAttributes.ID.getColumnName());
     //   System.out.println(UserDaoImpl.getAll.request());
    }
}