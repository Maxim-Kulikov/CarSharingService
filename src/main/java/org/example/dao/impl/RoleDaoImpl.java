package org.example.dao.impl;

import jakarta.transaction.Transactional;
import org.example.model.Role;
import org.example.dao.RoleDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@Repository
//@Transactional
@Component
//@ComponentScan
public class RoleDaoImpl implements RoleDao<Role> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<Role> get(long id) {
        Session session = sessionFactory.openSession();
        Optional<Role> roleEntity = Optional.ofNullable(session.get(Role.class, id));
        session.close();
        return roleEntity;
    }

    @Override
    public List<Role> getAll(){
        Session session = sessionFactory.openSession();
        List<Role> roles = loadAllData(Role.class, session);
        session.close();
        return roles;
    }

    @Override
    public void save(Role role) {

    }

    @Override
    public void update(Role role, String[] params) {

    }

    @Override
    public void delete(Role role) {

    }
}
