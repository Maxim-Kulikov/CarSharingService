package org.example.controller.user;

import lombok.AllArgsConstructor;
import org.example.dto.exception.UserNotFoundException;
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

    @GetMapping("/{id}")
    public List<UserExistedResp> get(@PathVariable Long id) throws UserNotFoundException {
        return List.of(userService.getExistedUser(id));
    }

    @GetMapping("")
    public List<UserExistedResp> get() throws UserNotFoundException {
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

    /*@PostMapping("/get/jwt")
    public ResponseEntity<String> getJwt(@RequestBody UserAuthReq dto){
        return userService.Jwt(dto);
    }*/

}
