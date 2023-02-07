package org.example.dao.impl;

import org.example.dao.UserDao;
import org.example.dao.config.ConnectionPool;
import org.example.models.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao<UserEntity> {
    private final String ID = "users.id",
            LOGIN = "users.login",
            PASSWORD = "users.password",
            ROLE_ID_IN_USERS = "users.idRole",
            EXTRA_USERS_ID_IN_USERS = "users.idExtraUsersdata",
            EXTRA_USERS_ID = "extraUsersData.id",
            PASSPORT_NUMBER = "extraUsersData.passport_number",
            NAME = "extraUsersData.name",
            LASTNAME = "extraUsersData.lastname",
            DATE_OF_BIRTH = "extraUsersData.dateOfBirth",
            DRIVING_LICENSE = "extraUsersData.drivingLicense",
            PHONE = "extraUsersData.phone",
            REGISTER_DATE = "extraUsersData.registerDate",
            ROLE = "roles.role",
            ROLE_ID = "roles.id";
    private final String GET_BY_ID = "SELECT " + ID + ", " + LOGIN + ", " + PASSWORD + ", "
            + ROLE + ", " + EXTRA_USERS_ID + ", " + PASSPORT_NUMBER + ", " + NAME + ", " + LASTNAME + ", "
            + DATE_OF_BIRTH + ", " + DRIVING_LICENSE + ", " + PHONE + ", " + REGISTER_DATE + ", idRole" +
            "FROM users " +
            "LEFT JOIN extraUsersData ON " + EXTRA_USERS_ID_IN_USERS + " = " + EXTRA_USERS_ID +
            "JOIN roles ON " + ROLE_ID_IN_USERS + " = " + ROLE_ID +
            "WHERE " + ID + " = ?";
    private final String GET_ALL = "SELECT " + ID + ", " + LOGIN + ", " + PASSWORD + ", "
            + ROLE + ", " + EXTRA_USERS_ID + ", " + PASSPORT_NUMBER + ", " + NAME + ", " + LASTNAME + ", "
            + DATE_OF_BIRTH + ", " + DRIVING_LICENSE + ", " + PHONE + ", " + REGISTER_DATE + ", idRole" +
            " FROM users " +
            "LEFT JOIN extraUsersData ON " + EXTRA_USERS_ID_IN_USERS + " = " + EXTRA_USERS_ID +
            " JOIN roles ON " + ROLE_ID_IN_USERS + " = " + ROLE_ID;
    private final String UPDATE = "UPDATE users SET login =  ? , password = ? WHERE users.id = ?";
    private final String DELETE = "DELETE FROM users WHERE users.id = ?";
    private final String INSERT = "INSERT INTO users (login,password,idRole) VALUES (?,?,?)";

    /*public static final String GET_ALL_USERS = "SELECT " + UsersAttributes.ID.columnName + ", "
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
*/


    @Override
    public Optional<UserEntity> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<UserEntity> getAll() throws SQLException {
        PreparedStatement ps = ConnectionPool.INSTANCE.getPool().getConnection().prepareStatement(GET_ALL);
        ResultSet rs = ps.executeQuery();
        return resultSetIntoUsers(rs);
    }

    @Override
    public void save(UserEntity userEntity) {

    }

    @Override
    public void update(UserEntity userEntity, String[] params) {

    }

    @Override
    public void delete(UserEntity userEntity) {

    }

    private List<UserEntity> resultSetIntoUsers(ResultSet resultSet) throws SQLException {
        List<UserEntity> userEntities = new ArrayList<>();
        while(resultSet.next()){
            userEntities.add(
                    UserEntity.builder()
                    .id(resultSet.getLong("id"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .roleEntity(resultSet.getInt("role"))
                    .build()
            );
        }
        return userEntities;
    }
}
