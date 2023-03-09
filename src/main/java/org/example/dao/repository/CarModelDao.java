package org.example.dao.repository;

import org.example.model.Car;
import org.example.model.CarModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelDao extends CrudRepository<CarModel, Integer> {
}
