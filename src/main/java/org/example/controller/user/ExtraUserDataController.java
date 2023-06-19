package org.example.controller.user;

import lombok.AllArgsConstructor;
import org.example.dto.carDTO.CarFilterReq;
import org.example.dto.sortenum.SortField;
import org.example.dto.sortenum.SortOrder;
import org.example.dto.userDTO.ExtraUserDataFilterReq;
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
    public List<ExtraUserDataResp> get(@PathVariable Long id) {
        return List.of(extraUserDataService.get(id));
    }

    @GetMapping()
    public List<ExtraUserDataResp> get(@RequestBody ExtraUserDataFilterReq filter) {
        return extraUserDataService.getAll(filter);
    }
}
