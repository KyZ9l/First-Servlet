package com.dmdev.http.dao;

import com.dmdev.http.entyty.Gender;
import com.dmdev.http.entyty.Role;
import com.dmdev.http.entyty.User;
import com.dmdev.http.util.ConnectionManeger;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL = """
            INSERT INTO users ( name, birthday, email, password, role, gender, image)
            VALUES (?,?,?,?,?,?,?);
            """;

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL = """
            SELECT id, name, birthday, email, image, password, role, gender
            FROM users
            WHERE email ? AND password =?
            """;

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManeger.get();
             var prepareStatement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL)) {
            prepareStatement.setString(1, email);
            prepareStatement.setString(2, password);

            var resultSet = prepareStatement.executeQuery();

            User user = null;


            if (resultSet.next()) {
                user = buildEntity(resultSet);
            }

            return Optional.ofNullable(user);

        }
    }


    @Override
    public List<User> findALL() {
        return null;
    }

    @Override
    public Optional<User> findByID(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (var connection = ConnectionManeger.get();
             var prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            prepareStatement.setObject(1, entity.getName());
            prepareStatement.setObject(2, entity.getBirthday());
            prepareStatement.setObject(3, entity.getEmail());
            prepareStatement.setObject(4, entity.getPassword());
            prepareStatement.setObject(5, entity.getRole().name());
            prepareStatement.setObject(6, entity.getGender().name());
            prepareStatement.setObject(7, entity.getImage());

            var update = prepareStatement.executeUpdate();
            var generatedKeys = prepareStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));

            return entity;
        }
    }

    private static User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id", Integer.class))
                .name(resultSet.getObject("name", String.class))
                .birthday(LocalDate.of(1995, 4, 11))
                .image(resultSet.getObject("image", String.class))
                .email(resultSet.getObject("email", String.class))
                .password(resultSet.getObject("password", String.class))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .gender(Gender.valueOf(resultSet.getObject("gender", String.class)))
                .build();
    }
}
