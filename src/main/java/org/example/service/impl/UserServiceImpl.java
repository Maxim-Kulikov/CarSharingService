package org.example.service.impl;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@ComponentScan("org.example")
//@Getter  Не знаю, что лучше, использовать методы или использовать методы через геттер
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserDao userDao;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final RoleDao roleDao;

    @Override
    @Transactional
    public List<UserExisted> getAll(){
        return userMapper.toUserExistedList((List<User>) userDao.findAll());
    }

    @Override
    @Transactional
    public Long save(UserAuthorizeRequest dto){
        return Optional.ofNullable(dto)
                .map(userMapper::toUser)
                .map(this::setRole)
                .map(this::setEmptyExtraUserData)
                .map(userDao::save)
                .orElseThrow(() -> new RuntimeException("Could not save user!"))
                .getId();
    }

    @Override
    public void delete(Long id){
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public Long findByLogin(String login) {
        return userDao.findFirstByLogin(login).get().getId();
    }

    @Override
    @Transactional
    public Long update(UserExisted dto) {
        User user = userDao.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Could not update user! Id does not exist!"));
        user.setLogin(dto.getLogin());
        userDao.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public Long authorize(UserAuthorizeRequest dto) {
        User user = userMapper.toUser(dto);
        user = userDao.findFirstByLoginAndPassword(user.getLogin(), user.getPassword()).get();
        return user.getId();
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
