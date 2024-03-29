package org.example.repository.user;

import org.example.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    Optional<User> findFirstByLoginAndPassword(String login, String password);
    Optional<User> findFirstByLogin(String login);
    Boolean existsByLogin(String login);

}
