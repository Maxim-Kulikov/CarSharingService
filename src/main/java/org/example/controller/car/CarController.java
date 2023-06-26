package org.example.controller.car;

import lombok.AllArgsConstructor;
import org.example.dto.carDTO.*;
import org.example.dto.exception.MarkNotFoundException;
import org.example.dto.exception.ModelNotFoundException;
import org.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private final CarService carService;

    @GetMapping("/description/{id}")
    public ResponseEntity<CarDescriptionResp> getDescription(@PathVariable Integer id) {
        return new ResponseEntity<>(carService.getCarDescription(id), HttpStatus.OK);
    }

    @PostMapping("/presentation")
    public ResponseEntity<List<CarInfoResp>> getAllPresentation(@RequestBody CarFilterReq filter) {
        return new ResponseEntity<>(carService.getAllCarsPresentation(filter), HttpStatus.OK);
    }

    @PostMapping("/description")
    public ResponseEntity<List<CarDescriptionResp>> getAllDescription(@RequestBody CarFilterReq filter) {
        return new ResponseEntity<>(carService.getAllCarsDescription(filter), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<CarDescriptionResp> save(@RequestBody CarCreateReq dto) throws MarkNotFoundException, ModelNotFoundException {
        return new ResponseEntity<>(carService.save(dto), HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<CarDescriptionResp> update(@RequestBody CarUpdateReq dto, @PathVariable Integer id) throws MarkNotFoundException, ModelNotFoundException {
        return new ResponseEntity<>(carService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        carService.delete(id);
    }

    @GetMapping("/presentation/{id}")
    public ResponseEntity<CarInfoResp> getPresentation(@PathVariable Integer id) {
        return new ResponseEntity<>(carService.getCarPresentation(id), HttpStatus.OK);
    }
}
