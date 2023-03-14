package org.example.dao.repository.user;

import org.example.model.ExtraUserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExtraUserDao extends CrudRepository<ExtraUserData, Long> {
    Optional<ExtraUserData> findFirstByPassportNumber(String passportNumber);
}
