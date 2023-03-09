package org.example.dao.repository;

import org.example.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarDao extends CrudRepository<Car, Integer> {
    Optional<Car> findFirstByCarModel_Mark_MarkAndCarModel_Model(String mark, String model);
    Optional<Car> findFirstById(int id);
    Optional<Car> findFirstByCarModel_Model(String model);
    Optional<Car> findFirstByCarModel_Mark_Mark(String mark);
    List<Car> findAllByCarModel_Model(String model);
    List<Car> findAllByCarModel_Mark_Mark(String mark);
    List<Car> findAllByCarModel_ModelAndCarModel_Mark_Mark(String model, String mark);


}
