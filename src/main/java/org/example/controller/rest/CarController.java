package org.example.controller.rest;

import lombok.AllArgsConstructor;
import org.example.dto.CarPresentationDto;
import org.example.service.impl.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/car")
public class CarController {

    @Autowired
    private final CarService carService;

    @GetMapping("/all")
    public List<CarPresentationDto> getAll(){
        return carService.getAllCarsPresentation();
    }

    @PostMapping("/save")
    public void save(@RequestParam int idModel,@RequestParam String number,@RequestParam int price,@RequestParam String limitations,@RequestParam int idImage){
        carService.save(idModel, number, price, limitations, idImage);
    }

}
