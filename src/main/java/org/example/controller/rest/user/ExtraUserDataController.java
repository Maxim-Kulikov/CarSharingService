package org.example.controller.rest.user;

import lombok.AllArgsConstructor;
import org.example.dto.userDTO.ExtraUserDataResponse;
import org.example.service.ExtraUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/user/data")
public class ExtraUserDataController {
    @Autowired
    private final ExtraUserDataService extraUserDataService;

    @GetMapping("/update")
    public Long update(@RequestBody ExtraUserDataResponse dto){
        return extraUserDataService.update(dto);
    }

    @GetMapping("/get/{id}")
    public ExtraUserDataResponse get(@PathVariable Long id){
        return extraUserDataService.get(id);
    }

    @GetMapping("/get/all")
    public List<ExtraUserDataResponse> getAll(){
        return extraUserDataService.getAll();
    }
}
