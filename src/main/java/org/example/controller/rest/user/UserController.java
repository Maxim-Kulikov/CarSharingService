package org.example.controller.rest.user;

import lombok.AllArgsConstructor;
import org.example.dto.userDTO.UserAuthorizeRequest;
import org.example.dto.userDTO.UserExisted;
import org.example.service.UserService;
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
    public Long save(@RequestBody UserAuthorizeRequest dto){
        return userService.save(dto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        userService.delete(id);
    }

    @PostMapping("/update")
    public Long update(@RequestBody UserExisted dto){
        return userService.update(dto);
    }


}
