package org.example.controller.user;

import lombok.AllArgsConstructor;
import org.example.dto.exception.UserIsExistedException;
import org.example.dto.exception.UserNotFoundException;
import org.example.dto.userDTO.UserAuthReq;
import org.example.dto.userDTO.UserExistedResp;
import org.example.dto.userDTO.UserUpdateReq;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserExistedResp> get(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getExistedUser(id), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<UserExistedResp>> get() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<UserExistedResp> save(@RequestBody UserAuthReq dto) throws UserIsExistedException {
        String login = dto.getLogin();
        String password = dto.getPassword();
        if (login == null || password == null || login.isBlank() || password.isBlank()) {
            throw new RuntimeException("User could not be saved, empty password or login");
        }
        return new ResponseEntity<>(userService.save(dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws UserNotFoundException {
        userService.delete(id);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserExistedResp> update(@RequestBody UserUpdateReq dto, @PathVariable Long id) throws UserNotFoundException, UserIsExistedException {
        return new ResponseEntity<>(userService.update(dto, id), HttpStatus.OK);
    }

    /*@PostMapping("/get/jwt")
    public ResponseEntity<String> getJwt(@RequestBody UserAuthReq dto){
        return userService.Jwt(dto);
    }*/

}
