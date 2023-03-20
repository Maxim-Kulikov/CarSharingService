package org.example.controller.rest.user;

import lombok.AllArgsConstructor;
import org.example.dto.userDTO.UserAuthorizeReq;
import org.example.dto.userDTO.UserExistedResp;
import org.example.dto.userDTO.UserUpdateReq;
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
    public List<UserExistedResp> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping("/save")
    public UserExistedResp save(@RequestBody UserAuthorizeReq dto){
        return userService.save(dto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        userService.delete(id);
    }

    @PatchMapping("/update/{id}")
    public UserExistedResp update(@RequestBody UserUpdateReq dto, @PathVariable Long id){
        return userService.update(dto, id);
    }


}
