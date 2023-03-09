package org.example.service.impl;

import lombok.EqualsAndHashCode;
import org.example.dao.repository.RoleDao;
import org.example.dao.repository.UserDao;
import org.example.dto.userDTO.UserAuthorizeRequest;
import org.example.dto.userDTO.UserExisted;
import org.example.mapper.UserMapper;
import org.example.model.ExtraUserData;
import org.example.model.Role;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@EqualsAndHashCode
@ComponentScan("org.example")
//@Getter  Не знаю, что лучше, использовать методы или использовать методы через геттер
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleDao roleDao;

    public List<UserExisted> getAll(){
        return userMapper.toUserExistedList((List<User>) userDao.findAll());
    }

    public UserExisted save(UserAuthorizeRequest dto){
        return Optional.ofNullable(dto)
                .map(userMapper::toUser)
                .map(this::setRole)
                .map(this::setEmptyExtraUserData)
                .map(userDao::save)
                .map(userMapper::toUserExisted)
                .orElseThrow(()->new RuntimeException("Can not save user!"));
    }

    public void delete(UserExisted dto){
        userDao.deleteById(dto.getId());
    }

    @Override
    public UserExisted findByLogin(String login) {
        return userMapper.toUserExisted(
                userDao.findFirstByLogin(login).get()
        );
    }

    @Override
    public UserExisted update(UserExisted dto) {
        User user = userDao.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Could not update user! Id does not exist!"));
        user.setLogin(dto.getLogin());
        userDao.save(user);
        return userMapper.toUserExisted(user);
    }

    @Override
    public UserExisted authorize(UserAuthorizeRequest dto) {
        User user = userMapper.toUser(dto);
        user = userDao.findFirstByLoginAndPassword(user.getLogin(), user.getPassword()).get();
        return userMapper.toUserExisted(user);
    }

    private User setRole(User user){
        user.setRole(findRole("user"));
        return user;
    }

    private User setEmptyExtraUserData(User user){
        user.setExtraUserData(new ExtraUserData());
        return user;
    }

    private Role findRole(String role){
        return roleDao.findFirstByRole(role).get();
    }


}
