package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
    /*@Autowired
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Override
    @Transactional
    public JwtTokenResp getJwtToken(UserAuthReq dto){
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));
            System.out.println(authentication);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Имя или пароль неправильны", e);
        }
        //String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getLogin());
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        String[] chunks = jwtToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        System.out.println("UserServiceImpl getJwtToken:\n" + new String(decoder.decode(chunks[0])));
        System.out.println(new String(decoder.decode(chunks[1])));
        return new JwtTokenResp(jwtToken);
    }
*/

    @Override
    @Transactional
    public List<UserExistedResp> getAll() {
        return userMapper.toUserExistedList((List<User>) userDao.findAll());
    }

    @Override
    @Transactional
    public UserExistedResp save(UserAuthReq dto) {
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
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public UserExistedResp findByLogin(String login) throws UserNotFoundException {
        User user = getUserOrThrowException(login);
        return userMapper.toUserExistedResp(user);
    }

    @Override
    @Transactional
    public UserExistedResp update(UserUpdateReq dto, Long id) throws UserNotFoundException {
        User user = getUserOrThrowException(id);
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

    private User setRole(User user) {
        user.setRole(findRole(ROLE_USER));
        return user;
    }

    private User setEmptyExtraUserData(User user) {
        user.setExtraUserData(new ExtraUserData());
        return user;
    }

    private Role findRole(String role) {
        return roleDao.findFirstByRole(role)
                .orElseThrow(() -> new RuntimeException(role + "was not found"));
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
