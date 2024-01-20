package com.example.student_lesson.manager;

import com.example.student_lesson.db.DBConnectionProvider;
import com.example.student_lesson.model.User;

import java.sql.*;


public class UserManager {


    private Connection connection = DBConnectionProvider.getInstance().getConnection();


    public void add(User user) {
        String sql = "INSERT INTO user(name,surname,email,password) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = '" + email + "'";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public User getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
