package org.example;

import org.example.dao.factories.DaoFactory;
import org.example.models.ExtraUserDataEntity;
import org.example.models.RoleEntity;
import org.example.models.UserEntity;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        /*List<UserEntity> users = DaoFactory.INSTANCE.getUserDao().getAll();
        users.forEach(user -> System.out.println(user.toString()));

        Optional<UserEntity> userEntity = DaoFactory.INSTANCE.getUserDao().get(252);
        System.out.println("----------- " + userEntity.get().getExtraUserDataEntity().getPassportNumber());
        */

        /*ExtraUserDataEntity extraUserData = ExtraUserDataEntity.builder()
                .name("sasha")
                .lastname("otraschenok")
                .phone("+375297456")
                .passportNumber("HB12345678")
                .build();

        Optional<RoleEntity> roleEntity = DaoFactory.INSTANCE.getRoleDao().get(1);

        UserEntity userEntity = UserEntity.builder()
                .extraUserDataEntity(extraUserData)
                .login("nekolover")
                .roleEntity(roleEntity.get())
                .password("1234")
                .build();

        DaoFactory.INSTANCE.getUserDao().save(userEntity);*/

        //DaoFactory.INSTANCE.getUserDao().delete(DaoFactory.INSTANCE.getUserDao().get(252).get());



    }

}