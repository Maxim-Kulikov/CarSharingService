package com.example.controller;

import com.example.service.PhotoService;
import com.example.dto.PhotoResp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private final PhotoService photoService;

    @PostMapping("/save/{file}/{idCar}")
    public String save(@PathVariable MultipartFile file, @PathVariable Integer idCar){
        return photoService.save(idCar, file);
    }

    @GetMapping("/get/{id}")
    public PhotoResp get(@PathVariable String id){
        return photoService.findById(id);
    }

    @GetMapping("/get/id_car/{idCar}")
    public PhotoResp getByCarId(@PathVariable Integer idCar){
        return photoService.findByCarId(idCar);
    }

    @GetMapping("/get/all/{idCar}")
    public List<PhotoResp> getAllByCarId(@PathVariable Integer idCar){
        return photoService.findAllByCarId(idCar);
    }
}
