package org.example.repository;

import org.example.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    Optional<Role> getRoleById(int id);
    Optional<Role> findFirstByRole(String role);
}