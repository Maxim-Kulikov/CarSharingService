package com.example.repository;

import com.example.document.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends MongoRepository<Photo, String> {
    Optional<Photo> findFirstByIdCar(Integer idCar);
    List<Photo> findAllByIdCar(Integer idCar);
}
