package org.example.dao.impl;

import org.example.dao.UserDao;
import org.example.dao.config.ConnectionPool;
import org.example.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao<User> {

    public enum UsersAttributes{
        ID("users.id"),
        LOGIN("users.login"),
        PASSWORD("users.password"),
        ID_ROLE("users.idRole"),
        ID_EXTRA_DATA("users.idExtraUsersData");

        String columnName;
        UsersAttributes(String columnName){
            this.columnName = columnName;
        }

    }

    public enum ExtraUsersDataAttributes{
        ID("extraUsersData.id"),
        PASSPORT_NUMBER("extraUsersData.passport_number"),
        NAME("extraUsersData.name"),
        LASTNAME("extraUsersData.lastname"),
        BIRTHDATE("extraUsersData.birthdate"),
        DRIVING_LICENSE("extraUsersData.drivingLicense"),
        PHONE("extraUsersData.phone"),
        REGISTER_DATE("extraUsersData.registerDate");

        String columnName;
        ExtraUsersDataAttributes(String columnName){
            this.columnName = columnName;
        }

    }

    public enum RolesAttributes{
        ID("roles.id"),
        ROLE("roles.role");

        String columnName;
        RolesAttributes(String columnName){
            this.columnName = columnName;
        }

    }
    public static final String GET_ALL_USERS = "SELECT " + UsersAttributes.ID.columnName + ", "
            + UsersAttributes.LOGIN.columnName + ", "
            + UsersAttributes.PASSWORD.columnName + ", "
            + UsersAttributes.ID_EXTRA_DATA.columnName + ", "
            + ExtraUsersDataAttributes.ID.columnName + ", "
            + ExtraUsersDataAttributes.NAME.columnName + ", "
            + ExtraUsersDataAttributes.LASTNAME.columnName + ", "
            + ExtraUsersDataAttributes.BIRTHDATE.columnName + ", "
            + ExtraUsersDataAttributes.PHONE.columnName + ", "
            + ExtraUsersDataAttributes.PASSPORT_NUMBER.columnName + ", "
            + ExtraUsersDataAttributes.DRIVING_LICENSE.columnName + ", "
            + ExtraUsersDataAttributes.REGISTER_DATE.columnName;



    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() throws SQLException {
        PreparedStatement ps = ConnectionPool.INSTANCE.getPool().getConnection().prepareStatement(GET_ALL_USERS);
        ResultSet rs = ps.executeQuery();
        return resultSetIntoUsers(rs);
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }

    private List<User> resultSetIntoUsers(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while(resultSet.next()){
            users.add(
                    User.builder()
                    .id(resultSet.getLong("id"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .role(resultSet.getInt("role"))
                    .build()
            );
        }
        return users;
    }
}
