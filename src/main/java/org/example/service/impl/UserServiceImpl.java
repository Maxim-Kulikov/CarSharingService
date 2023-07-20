package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dto.exception.RoleNotFoundException;
import org.example.dto.exception.UserIsExistedException;
import org.example.dto.exception.UserNotFoundException;
import org.example.repository.RoleDao;
import org.example.repository.user.UserDao;
import org.example.dto.userDTO.UserAuthReq;
import org.example.dto.userDTO.UserExistedResp;
import org.example.dto.userDTO.UserUpdateReq;
import org.example.mapper.user.UserMapper;
import org.example.model.ExtraUserData;
import org.example.model.Role;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final Short ROLE_USER = 2;
    private final Short ROLE_ADMIN = 1;
    @Autowired
    private final UserDao userDao;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final RoleDao roleDao;

    @Override
    @Transactional
    public List<UserExistedResp> getAll() {
        return userMapper.toUserExistedList((List<User>) userDao.findAll());
    }

    @Override
    @Transactional
    public UserExistedResp save(UserAuthReq dto) throws UserIsExistedException {
        if (!userDao.existsByLogin(dto.getLogin())) {
            return Optional.of(dto)
                    .map(userMapper::toUser)
                    .map(this::setRole)
                    .map(this::setEmptyExtraUserData)
                    .map(userDao::save)
                    .map(userMapper::toUserExistedResp)
                    .orElseThrow(() -> new RuntimeException("Could not save user!"));
        }
        throw new UserIsExistedException(dto.getLogin());
    }

    @Transactional
    @Override
    public void delete(Long id) throws UserNotFoundException {
        if (!userDao.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public User findByLogin(String login) throws UserNotFoundException {
        return getUserOrThrowException(login).changer().extraUserData(null).change();
    }

    @Override
    @Transactional
    public UserExistedResp update(UserUpdateReq dto, Long id) throws UserNotFoundException, UserIsExistedException {
        User user = getUserOrThrowException(id);
        if (userDao.existsByLogin(dto.getLogin())) {
            throw new UserIsExistedException(dto.getLogin());
        }
        user = updateUserWithChanger(user, dto);
        userDao.save(user);
        return userMapper.toUserExistedResp(user);

    }

    @Override
    @Transactional
    public UserExistedResp authorize(UserAuthReq dto) throws UserNotFoundException {
        User user = userMapper.toUser(dto);
        String login = user.getLogin();
        String password = user.getPassword();
        user = getUserOrThrowException(login, password);
        return userMapper.toUserExistedResp(user);
    }

    @Override
    @Transactional
    public UserExistedResp getExistedUser(Long id) throws UserNotFoundException {
        User user = getUserOrThrowException(id);
        return userMapper.toUserExistedResp(user);
    }

    private User setRole(User user) throws RoleNotFoundException {
        user.setRole(findRole(ROLE_USER));
        return user;
    }

    private User setEmptyExtraUserData(User user) {
        user.setExtraUserData(new ExtraUserData());
        return user;
    }

    private Role findRole(Short id) throws RoleNotFoundException {
        return roleDao.getRoleById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

    public User updateUserWithChanger(User model, UserUpdateReq dto) {
        return model.changer()
                .login(dto.getLogin())
                .password(dto.getPassword())
                .role(model.getRole())
                .extraUserData(model.getExtraUserData())
                .change();
    }

    private Optional<User> getOptionalOfUser(String login) {
        return userDao.findFirstByLogin(login);
    }

    private Optional<User> getOptionalOfUser(Long id) {
        return userDao.findById(id);
    }

    private Optional<User> getOptionalOfUser(String login, String password) {
        return userDao.findFirstByLoginAndPassword(login, password);
    }

    private User getUserOrThrowException(Long id) throws UserNotFoundException {
        return getOptionalOfUser(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private User getUserOrThrowException(String login) throws UserNotFoundException {
        return getOptionalOfUser(login)
                .orElseThrow(() -> new UserNotFoundException(login));
    }

    private User getUserOrThrowException(String login, String password) throws UserNotFoundException {
        return getOptionalOfUser(login, password)
                .orElseThrow(() -> new UserNotFoundException(login, password));
    }

}
