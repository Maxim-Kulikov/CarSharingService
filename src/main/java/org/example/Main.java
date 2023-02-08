package org.example;

import org.example.dao.factories.DaoFactory;
import org.example.models.UserEntity;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<UserEntity> user =  DaoFactory.INSTANCE.getUserDao().get(3);
        System.out.println(user.toString());
    }
}