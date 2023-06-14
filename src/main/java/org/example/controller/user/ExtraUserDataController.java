package org.example.controller.user;

import lombok.AllArgsConstructor;
import org.example.dto.userDTO.ExtraUserDataResp;
import org.example.dto.userDTO.ExtraUserDataUpdateReq;
import org.example.service.ExtraUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users/extra-data")
public class ExtraUserDataController {
    @Autowired
    private final ExtraUserDataService extraUserDataService;

    @PatchMapping("/update/{id}")
    public ExtraUserDataResp update(@RequestBody ExtraUserDataUpdateReq dto, @PathVariable Long id) {
        return extraUserDataService.update(dto, id);
    }

    @GetMapping("/{id}")
    public ExtraUserDataResp get(@PathVariable Long id) {
        return extraUserDataService.get(id);
    }

    @GetMapping("")
    public List<ExtraUserDataResp> getAll() {
        return extraUserDataService.getAll();
    }
}
