package org.example.controller.rest;

import lombok.AllArgsConstructor;
import org.example.dao.repository.CarModelDao;
import org.example.model.CarMark;
import org.example.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@ComponentScan("org.example")
@RequestMapping("/cars_list")
public class CarMarkAndModelController {
    @Autowired
    private CarModelDao carModelDao;

    @PostMapping("/save")
    public void save(@RequestParam String mark, @RequestParam String model){
        carModelDao.save(CarModel.builder()
                .model(model)
                .mark(CarMark.builder()
                                .mark(mark)
                                .build()
                ).build());
    }

    @GetMapping("/get_by_id")
    public CarModel get(@RequestParam int id){
        return carModelDao.findById(id).get();
    }
}
