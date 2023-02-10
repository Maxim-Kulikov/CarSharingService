package org.example.dao.impl;

import org.example.dao.RoleDao;
import org.example.models.RoleEntity;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RoleDaoImpl implements RoleDao<RoleEntity> {

    private SessionFactory sessionFactory;

    public RoleDaoImpl(){
        sessionFactory = HibernateSessionFactoryUtil.INSTANCE.getSessionFactory();
    }

    @Override
    public Optional<RoleEntity> get(long id) {
        Session session = sessionFactory.openSession();
        Optional<RoleEntity> roleEntity = Optional.ofNullable(session.get(RoleEntity.class, id));
        session.close();
        return roleEntity;
    }

    @Override
    public List<RoleEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(RoleEntity roleEntity) {

    }

    @Override
    public void update(RoleEntity roleEntity, String[] params) {

    }

    @Override
    public void delete(RoleEntity roleEntity) {

    }
}
