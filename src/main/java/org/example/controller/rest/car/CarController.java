package org.example.controller.rest.car;

import lombok.AllArgsConstructor;
import org.example.dto.carDTO.CarDescriptionResp;
import org.example.dto.carDTO.CarInfoResp;
import org.example.dto.carDTO.CarCreateReq;
import org.example.dto.carDTO.CarUpdateReq;
import org.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    //TODO get all by mark не работает
    @GetMapping("/get/presentation/all/{mark}")
    public List<CarInfoResp> getAllPresentationByMark(@PathVariable String mark){
        return carService.getAllCarsPresentationByMark(mark);
    }

    //TODO get all by model не работает
    @GetMapping("/get/presentation/all/{model}")
    public List<CarInfoResp> getAllPresentationByModel(@PathVariable String model){
        return carService.getAllCarsPresentationByModel(model);
    }

    @GetMapping("/get/presentation/all/mark_and_model")
    public List<CarInfoResp> getAllPresentation(@RequestParam String mark, @RequestParam String model){
        return carService.getAllCarsPresentationByMarkAndModel(mark, model);
    }

    @PostMapping(value = "/save/for/liza", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE} )
    public void saveForLiza(@RequestPart MultipartFile file, @RequestPart CarCreateReq dto){
        System.out.println(file.getName());
        System.out.println(dto.toString());
    }
}
