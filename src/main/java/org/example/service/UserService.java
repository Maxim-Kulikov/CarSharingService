package org.example.service;

import org.example.dto.exception.UserNotFoundException;
import org.example.dto.userDTO.UserAuthReq;
import org.example.dto.userDTO.UserExistedResp;
import org.example.dto.userDTO.UserUpdateReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserExistedResp> getAll();
    UserExistedResp save(UserAuthReq dto);
    void delete(Long id);
    UserExistedResp findByLogin(String login) throws UserNotFoundException;
    UserExistedResp update(UserUpdateReq dto, Long id) throws UserNotFoundException;
    UserExistedResp authorize(UserAuthReq dto) throws UserNotFoundException;
    UserExistedResp getExistedUser(Long id) throws UserNotFoundException;
   // JwtTokenResp getJwtToken(UserAuthReq dto);

}
