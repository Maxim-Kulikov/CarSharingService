package org.example.controller.rest.car;

import lombok.AllArgsConstructor;
import org.example.dto.carDTO.CarDescriptionDto;
import org.example.dto.carDTO.CarPresentationDto;
import org.example.dto.carDTO.CarCreationDto;
import org.example.service.CarService;
import org.example.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/car")
public class CarController {
    @Autowired
    private final CarService carService;

    @GetMapping("/get/description/{id}")
    public CarDescriptionDto getDescription(@PathVariable Integer id){
        return carService.getCarDescription(id);
    }

    @GetMapping("/get/description")
    public CarDescriptionDto getDescription(@RequestBody CarPresentationDto dto){
        return carService.getCarDescription(dto);
    }

    @GetMapping("/get/presentation/all")
    public List<CarPresentationDto> getAllPresentation(){
        return carService.getAllCarsPresentation();
    }

    @GetMapping("/get/description/all")
    public List<CarDescriptionDto> getAllDescription(){
        return carService.getAllCarsDescription();
    }

    @PostMapping("/save")
    public Integer save(@RequestBody CarCreationDto dto){
        return carService.save(dto);
    }

    @PatchMapping("/update")
    public Integer update(@RequestBody CarDescriptionDto dto){
        return carService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        carService.delete(id);
    }

    @GetMapping("/get/presentation/{id}")
    public CarPresentationDto getPresentation(@PathVariable Integer id){
        return carService.getCarPresentation(id);
    }

    @GetMapping("/get/presentation/all/{mark}")
    public List<CarPresentationDto> getAllPresentationByMark(@PathVariable String mark){
        return carService.getAllCarsPresentationByMark(mark);
    }

    @GetMapping("/get/presentation/all/{model}")
    public List<CarPresentationDto> getAllPresentationByModel(@PathVariable String model){
        return carService.getAllCarsPresentationByModel(model);
    }

    @GetMapping("/get/presentation/all/mark_and_model")
    public List<CarPresentationDto> getAllPresentation(@RequestParam String mark, @RequestParam String model){
        return carService.getAllCarsPresentationByMarkAndModel(mark, model);
    }
}
