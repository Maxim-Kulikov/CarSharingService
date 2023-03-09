package org.example.controller.rest;

import lombok.AllArgsConstructor;
import org.example.dto.userDTO.UserAuthorizeRequest;
import org.example.dto.userDTO.UserExisted;
import org.example.model.User;
import org.example.service.UserService;
import org.example.service.impl.RoleService;
import org.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserExisted> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping("/save")
    public UserExisted save(UserAuthorizeRequest dto){
        return userService.save(dto);
    }

}
