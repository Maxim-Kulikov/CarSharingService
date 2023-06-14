package org.example.controller.user;

import lombok.AllArgsConstructor;
import org.example.controller.exception.UserNotFoundException;
import org.example.dto.userDTO.UserAuthReq;
import org.example.dto.userDTO.UserExistedResp;
import org.example.dto.userDTO.UserUpdateReq;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserExistedResp> getAll() {
        return userService.getAll();
    }

    @PostMapping("/save")
    public UserExistedResp save(@RequestBody UserAuthReq dto) {
        if (dto.getLogin().isBlank() || dto.getPassword().isBlank()) {
            throw new RuntimeException("User could not be saved, empty password or login");
        }
        return userService.save(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PatchMapping("/update/{id}")
    public UserExistedResp update(@RequestBody UserUpdateReq dto, @PathVariable Long id) throws UserNotFoundException {
        return userService.update(dto, id);
    }

    @GetMapping("/{id}")
    public UserExistedResp get(@PathVariable Long id) throws UserNotFoundException {
        return userService.getExistedUser(id);
    }

    /*@PostMapping("/get/jwt")
    public ResponseEntity<String> getJwt(@RequestBody UserAuthReq dto){
        return userService.Jwt(dto);
    }*/

}
