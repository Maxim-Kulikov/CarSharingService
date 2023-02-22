package org.example.service;

import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@EqualsAndHashCode
@ComponentScan("org.example")
//@Getter  Не знаю, что лучше, использовать методы или использовать методы через геттер
//@Component
public class UserService  {

    /*@Autowired
    private UserDao userDao;

    public List<User> getAllUsers(){
        return (List<User>) userDao.findAll();
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password){
        return userDao.findFirstByLoginAndPassword(login, password);
    }

    public void addUserInDB(User user){
        userDao.save(user);
    }*/
}
