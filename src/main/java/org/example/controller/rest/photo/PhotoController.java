package org.example.controller.rest.photo;

import lombok.AllArgsConstructor;
import org.example.dto.PhotoResp;
import org.example.kafka.producer.PhotoProducer;
import org.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private final PhotoProducer photoProducer;
    @Autowired
    private final PhotoService photoService;

    @PostMapping("/save/{idCar}")
    public void save(@RequestBody MultipartFile file, @PathVariable Integer idCar) throws IOException {
        photoProducer.send(idCar, file);
    }

    @GetMapping("/get/id_car/{idCar}")
    public List<PhotoResp> getByCarId(@PathVariable Integer idCar){
        return photoService.getByCarId(idCar);
    }
}
