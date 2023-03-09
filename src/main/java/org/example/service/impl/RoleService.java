package org.example.service.impl;

import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.example.dao.repository.RoleDao;
import org.example.dao.repository.UserDao;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@EqualsAndHashCode
@ComponentScan("org.example")
public class RoleService {
    @Autowired
    private final RoleDao roleDao;
    @Autowired
    private final UserDao userDao;

    public List<Role> getAllRoles(){
        return (List<Role>) roleDao.findAll();
    }

    public Optional<Role> getRoleById(int id){
        return roleDao.getRoleById(id);
    }

    public void assignUserRole(Long idUser, int idRole){

    }
}
