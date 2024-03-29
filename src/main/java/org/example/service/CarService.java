package org.example.service;

import org.example.dto.carDTO.*;
import org.example.dto.exception.CarNotFoundException;
import org.example.dto.exception.MarkNotFoundException;
import org.example.dto.exception.ModelNotFoundException;

import java.util.List;

public interface CarService {
    CarInfoResp getCarPresentation(Integer id) throws CarNotFoundException;
    List<CarInfoResp> getAllCarsPresentation(CarFilterReq filter);
    List<CarDescriptionResp> getAllCarsDescription(CarFilterReq filter);
    CarDescriptionResp getCarDescription(Integer id) throws CarNotFoundException;
    CarDescriptionResp update(CarUpdateReq dto, Integer id) throws MarkNotFoundException, ModelNotFoundException, CarNotFoundException;
    CarDescriptionResp save(CarCreateReq dto) throws MarkNotFoundException, ModelNotFoundException;
    void delete(Integer id);
}
