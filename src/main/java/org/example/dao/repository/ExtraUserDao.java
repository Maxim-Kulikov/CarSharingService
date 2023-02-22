package org.example.dao.repository;

import org.example.model.ExtraUserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraUserDao extends CrudRepository<ExtraUserData, Long> {
}
