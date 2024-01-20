package com.example.student_lesson.manager;


import com.example.student_lesson.db.DBConnectionProvider;
import com.example.student_lesson.model.Lesson;
import com.example.student_lesson.model.Student;
import com.example.student_lesson.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {


    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    private UserManager userManager = new UserManager();


    public List<Lesson> getAllLessons() {
        String sql = "SELECT * FROM lesson";
        List<Lesson> lessons = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                lessons.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getDouble("duration"))
                        .lecturerName(resultSet.getString("lecturer_Name"))
                        .price(resultSet.getDouble("price"))
                        .user(userManager.getUserById(resultSet.getInt("user_Id")))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }

    public void add(Lesson lesson) {
        String sql = "INSERT INTO lesson(name,duration,lecturer_Name,price,user_Id) VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setDouble(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.setInt(5, lesson.getUser().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                lesson.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM lesson WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Lesson getLessonById(int id) {
        String sql = "SELECT * FROM lesson WHERE id = " + id;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getDouble("duration"))
                        .lecturerName(resultSet.getString("lecturer_Name"))
                        .price(resultSet.getDouble("price"))
                        .user(userManager.getUserById(resultSet.getInt("user_Id")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(Lesson lesson) {
        String sql = "UPDATE lesson SET name = ?, duration = ?, lecturer_Name = ?, price = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setDouble(2, lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
            preparedStatement.setInt(5, lesson.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Lesson getLessonByName(String name) {
        String sql = "SELECT * FROM lesson WHERE name = '" + name + "'";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getDouble("duration"))
                        .lecturerName(resultSet.getString("lecturer_Name"))
                        .price(resultSet.getDouble("price"))
                        .user(userManager.getUserById(resultSet.getInt("user_Id")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }



    public List<Lesson> getAllLessonsByUserId(int id) {
        String sql = "SELECT * FROM lesson WHERE user_Id = '" + id + "'";
        List<Lesson> lessons = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                lessons.add(Lesson.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .duration(resultSet.getDouble("duration"))
                        .lecturerName(resultSet.getString("lecturer_Name"))
                        .price(resultSet.getDouble("price"))
                        .user(userManager.getUserById(resultSet.getInt("user_Id")))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }
}


