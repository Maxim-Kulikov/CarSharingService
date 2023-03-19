package org.example.controller.rest.car;

import lombok.AllArgsConstructor;
import org.example.dto.carDTO.CarDescriptionResp;
import org.example.dto.carDTO.CarInfoResp;
import org.example.dto.carDTO.CarCreateReq;
import org.example.dto.carDTO.CarUpdateReq;
import org.example.service.CarService;
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
    public CarDescriptionResp getDescription(@PathVariable Integer id){
        return carService.getCarDescription(id);
    }

    @GetMapping("/get/description")
    public CarDescriptionResp getDescription(@RequestBody CarInfoResp dto){
        return carService.getCarDescription(dto);
    }

    @GetMapping("/get/presentation/all")
    public List<CarInfoResp> getAllPresentation(){
        return carService.getAllCarsPresentation();
    }

    @GetMapping("/get/description/all")
    public List<CarDescriptionResp> getAllDescription(){
        return carService.getAllCarsDescription();
    }

    @PostMapping("/save")
    public CarDescriptionResp save(@RequestBody CarCreateReq dto){
        return carService.save(dto);
    }

    @PatchMapping("/update/{id}")
    public CarDescriptionResp update(@RequestBody CarUpdateReq dto, @PathVariable Integer id){
        return carService.update(dto, id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        carService.delete(id);
    }

    @GetMapping("/get/presentation/{id}")
    public CarInfoResp getPresentation(@PathVariable Integer id){
        return carService.getCarPresentation(id);
    }

    @GetMapping("/get/presentation/all/{mark}")
    public List<CarInfoResp> getAllPresentationByMark(@PathVariable String mark){
        return carService.getAllCarsPresentationByMark(mark);
    }

    @GetMapping("/get/presentation/all/{model}")
    public List<CarInfoResp> getAllPresentationByModel(@PathVariable String model){
        return carService.getAllCarsPresentationByModel(model);
    }

    @GetMapping("/get/presentation/all/mark_and_model")
    public List<CarInfoResp> getAllPresentation(@RequestParam String mark, @RequestParam String model){
        return carService.getAllCarsPresentationByMarkAndModel(mark, model);
    }
}