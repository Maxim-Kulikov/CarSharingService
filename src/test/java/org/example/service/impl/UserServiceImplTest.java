package org.example.service.impl;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserServiceImpl.class})
class UserServiceImplTest {

    private final String ROLE_USER = "ROLE_USER";
    private final String LOGIN = "kulikov.m.a102";
    private final String PASSWORD = "12345678";
    private final String DRIVING_LICENSE = "12345678AB";
    private final String PASSPORT_NUMBER = "HB12345678";
    private final Date BIRTHDATE = Date.valueOf("2004-03-18");
    private final Date REGISTER_DATE = Date.valueOf("2020-10-10");
    private final String NAME = "Maxim";
    private final String LASTNAME = "Kulikov";
    private final String PHONE = "297644167";
    private final Long ID_USER = 1L;
    private final Short ID_ROLE = 1;
    private final Optional<User> user = getUser();
    private final Optional<UserExistedResp> userExistedResp = getUserExistedResp();
    private final Optional<UserAuthReq> userAuthorizeReq = getUserAuthorizeReq();
    private final Optional<UserUpdateReq> userUpdateReq = getUserUpdateReq();
    @MockBean
    private UserDao userDao;

    @MockBean
    private RoleDao roleDao;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    UserService userService;

    @Test
    void getAll() {
        List<User> users = List.of(new User(), new User());
        List<UserExistedResp> userExistedResps = List.of(new UserExistedResp(), new UserExistedResp());

        when(userDao.findAll()).thenReturn(users);
        when(userMapper.toUserExistedList(users)).thenReturn(userExistedResps);

        List<UserExistedResp> result = userService.getAll();

        assertAll(() -> {
            assertNotNull(result);
            assertEquals(result, userExistedResps);
        }
        );
    }

    @Test
    void save() {
        when(roleDao.findFirstByRole(ROLE_USER)).thenReturn(getRole());
        when(userMapper.toUser(userAuthorizeReq.get())).thenReturn(user.get());
        when(userMapper.toUserExistedResp(user.get())).thenReturn(userExistedResp.get());
        when(userDao.save(user.get())).thenReturn(user.get());

        UserExistedResp expected = userMapper.toUserExistedResp(user.get());
        UserExistedResp result = userService.save(userAuthorizeReq.get());

        assertAll(() -> {
                    assertNotNull(result);
                    assertEquals(result, expected);
                }
        );
    }

    /*@Test
    void delete() {

    }*/

    @Test
    void findByLogin() {
        when(userDao.findFirstByLogin(LOGIN)).thenReturn(getUser());
        when(userMapper.toUserExistedResp(getUser().get())).thenReturn(getUserExistedResp().get());

        UserExistedResp expected = getUserExistedResp().get();
        UserExistedResp result = userService.findByLogin(LOGIN);

        assertAll(() -> {
                    assertNotNull(result);
                    assertEquals(result, expected);
                }
        );
    }

    @Test
    void update() {

        when(userDao.findById(ID_USER)).thenReturn(user);
        when(userDao.save(user.get())).thenReturn(user.get());
        when(userMapper.toUserExistedResp(user.get())).thenReturn(userExistedResp.get());

        UserExistedResp expected = userExistedResp.get();
        UserExistedResp result = userService.update(userUpdateReq.get(), ID_USER);

        assertAll(() -> {
                    assertNotNull(result);
                    assertEquals(result, expected);
                }
        );
    }

    @Test
    void authorize() {
        when(userMapper.toUser(userAuthorizeReq.get())).thenReturn(user.get());
        when(userDao.findFirstByLoginAndPassword(user.get().getLogin(), user.get().getPassword()))
                .thenReturn(user);
        when(userMapper.toUserExistedResp(user.get())).thenReturn(userExistedResp.get());

        UserExistedResp expected = userExistedResp.get();
        UserExistedResp result = userService.authorize(userAuthorizeReq.get());

        assertAll(() -> {
                    assertNotNull(result);
                    assertEquals(result, expected);
        }
        );
    }

    private Optional<User> getUser(){
        return Optional.ofNullable(User.builder()
                .id(ID_USER)
                .login(LOGIN)
                .password(PASSWORD)
                .extraUserData(getExtraUserData().get())
                .role(getRole().get())
                .build());
    }

    private Optional<ExtraUserData> getExtraUserData(){
        return Optional.ofNullable(ExtraUserData.builder()
                .id(ID_USER)
                .name(NAME)
                .lastname(LASTNAME)
                .passportNumber(PASSPORT_NUMBER)
                .phone(PHONE)
                .drivingLicense(DRIVING_LICENSE)
                .birthdate(BIRTHDATE)
                .registerDate(REGISTER_DATE)
                .build());
    }

    private Optional<UserAuthReq> getUserAuthorizeReq(){
        return Optional.ofNullable(UserAuthReq.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .build());
    }

    private Optional<Role> getRole(){
        return Optional.ofNullable(Role.builder()
                .id(ID_ROLE)
                .role(ROLE_USER)
                .build());
    }

    private Optional<UserExistedResp> getUserExistedResp(){
        return Optional.ofNullable(UserExistedResp.builder()
                .id(ID_USER)
                .role(ROLE_USER)
                .login(LOGIN)
                .build());
    }

    private Optional<UserUpdateReq> getUserUpdateReq(){
        return Optional.ofNullable(UserUpdateReq.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .build());
    }

}