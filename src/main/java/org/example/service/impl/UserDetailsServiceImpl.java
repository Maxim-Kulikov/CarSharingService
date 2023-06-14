//package org.example.service.impl;
//
//import lombok.AllArgsConstructor;
//import org.example.dao.repository.user.UserDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private final UserDao userDao;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userDao.findFirstByLogin(username)
//                .orElseThrow(()->{
//                    throw new UsernameNotFoundException("user not found");
//                });
//    }
//}
