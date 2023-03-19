package org.example.service;

import org.example.dto.userDTO.UserAuthorizeReq;
import org.example.dto.userDTO.UserExistedResp;
import org.example.dto.userDTO.UserUpdateReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserExistedResp> getAll();
    UserExistedResp save(UserAuthorizeReq dto);
    void delete(Long id);
    UserExistedResp findByLogin(String login);
    UserExistedResp update(UserUpdateReq dto, Long id);
    UserExistedResp authorize(UserAuthorizeReq dto);

}
