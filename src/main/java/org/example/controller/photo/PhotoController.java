package org.example.controller.photo;

import lombok.AllArgsConstructor;
import org.example.dto.exception.CarNotFoundException;
import org.example.dto.exception.PhotoNotFoundException;
import org.example.dto.photoDTO.PhotoRespDto;
import org.example.dto.photoDTO.SavePhotoDto;
import org.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/photos")
public class PhotoController {
    @Autowired
    private final PhotoService photoService;

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void save(@RequestParam MultipartFile file, @RequestParam(name = "id-car")Integer idCar) throws IOException, CarNotFoundException {
        photoService.save(file, idCar);
    }

    @GetMapping("")
    public ResponseEntity<List<PhotoRespDto>> getAll(@RequestParam(value = "id-car", required = false) Integer idCar) {
        return photoService.getAll(idCar);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoRespDto> get(@PathVariable String id)  {
        return photoService.get(id);
    }

}
