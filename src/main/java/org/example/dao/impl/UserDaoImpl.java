package org.example.dao.impl;

import jakarta.transaction.Transactional;
import org.example.model.User;
import org.example.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository
//@Transactional
@Component
public class UserDaoImpl implements UserDao<User> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(sessionFactory.openSession().get(User.class, id));
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        List<User> users = loadAllData(User.class, session);
        session.close();
        return users;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        session.flush();
        transaction.commit();
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(user);
        session.flush();
        transaction.commit();
    }

    private List<User> resultSetIntoUsers(ResultSet resultSet) throws SQLException {
        List<User> userEntities = new ArrayList<>();
        /*while(resultSet.next()){
            userEntities.add(
                    UserEntity.builder()
                    .id(resultSet.getLong("id"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .roleEntity(resultSet.getInt("role"))
                    .build()
            );
        }*/
        return userEntities;
    }


}
