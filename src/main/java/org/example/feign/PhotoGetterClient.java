package org.example.feign;

import org.example.dto.PhotoResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "CarSharingService", url = "http://localhost:8081/photo")
public interface PhotoGetterClient {
    @GetMapping(value = "/get/id_car/{idCar}")
    List<PhotoResp> getByCarId(@PathVariable Integer idCar);
}
