package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dao.repository.CarModelDao;
import org.example.model.CarMark;
import org.example.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@ComponentScan("org.example")
public class CarListService {
    @Autowired
    private final CarModelDao carModelDao;

    public CarModel findById(int id){
        return carModelDao.findById(id).get();
    }

}
