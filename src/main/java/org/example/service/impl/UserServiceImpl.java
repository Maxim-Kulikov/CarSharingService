package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.example.dao.repository.RoleDao;
import org.example.dao.repository.user.UserDao;
import org.example.dto.userDTO.UserAuthorizeReq;
import org.example.dto.userDTO.UserExistedResp;
import org.example.dto.userDTO.UserUpdateReq;
import org.example.mapper.user.UserMapper;
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

    private final String ROLE_USER = "ROLE_USER";
    private final String ROLE_ADMIN = "ROLE_ADMIN";
    @Autowired
    private final UserDao userDao;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final RoleDao roleDao;

    @Override
    @Transactional
    public List<UserExistedResp> getAll(){
        return userMapper.toUserExistedList((List<User>) userDao.findAll());
    }

    @Override
    @Transactional
    public UserExistedResp save(UserAuthorizeReq dto){
        return Optional.ofNullable(dto)
                .map(userMapper::toUser)
                .map(this::setRole)
                .map(this::setEmptyExtraUserData)
                .map(userDao::save)
                .map(userMapper::toUserExistedResp)
                .orElseThrow(() -> new RuntimeException("Could not save user!"));

    }

    @Transactional
    @Override
    public void delete(Long id){
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public UserExistedResp findByLogin(String login) {
     /*   return userMapper.toUserExistedResp(
                userDao.findFirstByLogin(login)
                .orElseThrow(() -> new RuntimeException("Could find user by this login!"))
        );*/
        return Optional.ofNullable(userDao.findFirstByLogin(login))
                .orElseThrow(() -> new RuntimeException("Could not find user!"))
                .map(userMapper::toUserExistedResp)
                .get();
    }

    @Override
    @Transactional
    public UserExistedResp update(UserUpdateReq dto, Long id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not update user! Id does not exist!"));
        user = updateUserWithChanger(user, dto);
        userDao.save(user);
        return userMapper.toUserExistedResp(user);
    }

    @Override
    @Transactional
    public UserExistedResp authorize(UserAuthorizeReq dto) {
        User user = userMapper.toUser(dto);
        user = userDao.findFirstByLoginAndPassword(user.getLogin(), user.getPassword())
                .orElseThrow(() -> new RuntimeException("This user does not exist!"));
        return userMapper.toUserExistedResp(user);
    }

    private User setRole(User user){
        user.setRole(findRole(ROLE_USER));
        return user;
    }

    private User setEmptyExtraUserData(User user){
        user.setExtraUserData(new ExtraUserData());
        return user;
    }

    private Role findRole(String role){
        return roleDao.findFirstByRole(role)
                .orElseThrow(() -> new RuntimeException("Could not find this role!"));
    }

    public User updateUser(User model, UserUpdateReq dto){
        return User.builder()
                .id(model.getId())
                .login(dto.getLogin().equals(model.getLogin()) || dto.getLogin().isEmpty()
                        ? model.getLogin() : dto.getLogin())
                .password(model.getPassword())
                .build();
    }

    public User updateUserWithChanger(User model, UserUpdateReq dto){
        return model.changer()
                .id(model.getId())
                .login(dto.getLogin().equals(model.getLogin()) || dto.getLogin().isEmpty()
                        ? model.getLogin() : dto.getLogin())
                .password(model.getLogin())
                .role(model.getRole())
                .extraUserData(model.getExtraUserData())
                .change();
    }

}
