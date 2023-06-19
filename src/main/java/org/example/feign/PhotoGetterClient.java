package org.example.feign;

import org.example.dto.photoDTO.PhotoRespDto;
import org.example.dto.photoDTO.SavePhotoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "photo-client", url = "http://localhost:8081/photos")
public interface PhotoGetterClient {
    @GetMapping(value = "")
    ResponseEntity<List<PhotoRespDto>> getAll(@RequestParam(value = "id-car", required = false) Integer idCar);

    @GetMapping("/{id}")
    ResponseEntity<PhotoRespDto> get(@PathVariable String id);
}
