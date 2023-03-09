package org.example.service;

import org.example.dto.userDTO.UserAuthorizeRequest;
import org.example.dto.userDTO.UserExisted;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserExisted> getAll();
    UserExisted save(UserAuthorizeRequest dto);
    void delete(UserExisted dto);
    UserExisted findByLogin(String login);
    UserExisted update(UserExisted dto);
    UserExisted authorize(UserAuthorizeRequest dto);

}
