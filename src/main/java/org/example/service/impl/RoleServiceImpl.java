package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.example.repository.RoleDao;
import org.example.repository.user.UserDao;
import org.example.model.Role;
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
public class RoleServiceImpl {
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
