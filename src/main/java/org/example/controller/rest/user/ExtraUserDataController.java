package org.example.controller.rest.user;

import lombok.AllArgsConstructor;
import org.example.dto.userDTO.ExtraUserDataResp;
import org.example.dto.userDTO.ExtraUserDataUpdateReq;
import org.example.service.ExtraUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user/data")
public class ExtraUserDataController {
    @Autowired
    private final ExtraUserDataService extraUserDataService;

    @GetMapping("/update/{id}")
    public ExtraUserDataResp update(@RequestBody ExtraUserDataUpdateReq dto, @PathVariable Long id){
        return extraUserDataService.update(dto, id);
    }

    @GetMapping("/get/{id}")
    public ExtraUserDataResp get(@PathVariable Long id){
        return extraUserDataService.get(id);
    }

    @GetMapping("/get/all")
    public List<ExtraUserDataResp> getAll(){
        return extraUserDataService.getAll();
    }
}
