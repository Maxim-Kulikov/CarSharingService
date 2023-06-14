package org.example.controller.photo;

import lombok.AllArgsConstructor;
import org.example.dto.photoDTO.PhotoRespDto;
import org.example.dto.photoDTO.SavePhotoDto;
import org.example.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    private final PhotoService photoService;

    @PostMapping("/save")
    public void save(@RequestBody SavePhotoDto dto) throws IOException {
        photoService.save(dto);
    }

    @GetMapping("")
    public List<PhotoRespDto> getAll(@RequestParam(value = "id-car", required = false) Integer idCar) {
        if (idCar == null) {
            return photoService.getAll();
        }
        return photoService.getAll(idCar);
    }

    @GetMapping("/{id}")
    public PhotoRespDto get(@PathVariable String id) {
        return photoService.get(id);
    }

}
