package org.example.controller.rest;

import lombok.AllArgsConstructor;
import org.example.dto.carDTO.CarPresentationDto;
import org.example.dto.carDTO.CarCreationDto;
import org.example.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/car")
public class CarController {

    @Autowired
    private final CarServiceImpl carServiceImpl;

    @GetMapping("/get/all")
    public List<CarPresentationDto> getAll(){
        return carServiceImpl.getAllCarsPresentation();
    }

    @PostMapping("/save")
    public Integer save(@RequestBody CarCreationDto dto){
        return carServiceImpl.save(dto);
    }

}
