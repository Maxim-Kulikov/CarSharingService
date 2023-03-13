package org.example.service;

import org.example.dto.userDTO.UserAuthorizeRequest;
import org.example.dto.userDTO.UserExisted;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserExisted> getAll();
    Long save(UserAuthorizeRequest dto);
    void delete(Long id);
    Long findByLogin(String login);
    Long update(UserExisted dto);
    Long authorize(UserAuthorizeRequest dto);

}
