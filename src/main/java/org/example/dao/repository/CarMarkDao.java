package org.example.dao.repository;

import org.example.model.CarMark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMarkDao extends CrudRepository<CarMark, Integer> {
}
