package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.RegisterUserDto;
import org.example.mapper.UserMapper;
import org.example.mapper.UserMapperI;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

/*
    @PostMapping("/register")
    public @ResponseBody RegisterUserDto registerUser(@RequestBody RegisterUserDto registerUserDto, Model model){
        if(registerUserDto == null)
            return null;

        userService.addUserInDB(
                userMapper.registerUserDtoToUserModel(registerUserDto)
        );
        model.addAttribute("user", registerUserDto);
        return registerUserDto;
    }
*/

    @GetMapping(value = "/hello")
    public String getName(){
        return "maxim";
    }
}
