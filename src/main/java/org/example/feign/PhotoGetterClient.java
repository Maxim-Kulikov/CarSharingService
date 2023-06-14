package org.example.feign;

import org.example.dto.photoDTO.PhotoRespDto;
import org.example.dto.photoDTO.SavePhotoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "photo-client", url = "http://localhost:8081/photos")
public interface PhotoGetterClient {
    @GetMapping(value = "")
    List<PhotoRespDto> getByCarId(@RequestParam("id-car") Integer idCar);

    @GetMapping("")
    List<PhotoRespDto> getAll();

    @GetMapping("/{id}")
    PhotoRespDto getById(@PathVariable String id);
}
