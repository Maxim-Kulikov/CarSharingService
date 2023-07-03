package org.example.controller.user;

import lombok.AllArgsConstructor;
import org.example.dto.carDTO.CarFilterReq;
import org.example.dto.exception.UserNotFoundException;
import org.example.dto.sortenum.SortField;
import org.example.dto.sortenum.SortOrder;
import org.example.dto.userDTO.ExtraUserDataFilterReq;
import org.example.dto.userDTO.ExtraUserDataResp;
import org.example.dto.userDTO.ExtraUserDataUpdateReq;
import org.example.service.ExtraUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users/extra-data")
public class ExtraUserDataController {
    @Autowired
    private final ExtraUserDataService extraUserDataService;

    @PatchMapping("/update/{id}")
    public ResponseEntity<ExtraUserDataResp> update(@RequestBody ExtraUserDataUpdateReq dto, @PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(extraUserDataService.update(dto, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtraUserDataResp> get(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(extraUserDataService.get(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<List<ExtraUserDataResp>> get(@RequestBody ExtraUserDataFilterReq filter) {
        return new ResponseEntity<>(extraUserDataService.getAll(filter), HttpStatus.OK);
    }
}
