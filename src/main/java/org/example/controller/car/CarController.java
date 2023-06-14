package org.example.controller.car;

import lombok.AllArgsConstructor;
import org.example.dto.carDTO.*;
import org.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private final CarService carService;

    @GetMapping("/{id}")
    public CarDescriptionResp getDescription(@PathVariable Integer id) {
        return carService.getCarDescription(id);
    }

    @GetMapping("/presentation")
    public List<CarInfoResp> getAllPresentation(CarFilterReq filter) {
        return carService.getAllCarsPresentation(filter);
    }

    @GetMapping("/description")
    public List<CarDescriptionResp> getAllDescription(CarFilterReq filter) {
        return carService.getAllCarsDescription(filter);
    }

    @PostMapping("/save")
    public CarDescriptionResp save(@RequestBody CarCreateReq dto) {
        return carService.save(dto);
    }

    @PatchMapping("/update/{id}")
    public CarDescriptionResp update(@RequestBody CarUpdateReq dto, @PathVariable Integer id) {
        return carService.update(dto, id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        carService.delete(id);
    }

    @GetMapping("/presentation/{id}")
    public CarInfoResp getPresentation(@PathVariable Integer id) {
        return carService.getCarPresentation(id);
    }

    /*@PostMapping(value = "/save/for/liza", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public void saveForLiza(@RequestPart MultipartFile file, @RequestPart CarCreateReq dto) {
        System.out.println(file.getName());
        System.out.println(dto.toString());
    }*/
}
