package com.example.student_lesson.manager;


import com.example.student_lesson.db.DBConnectionProvider;
import com.example.student_lesson.model.Lesson;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {


    Connection connection = DBConnectionProvider.getInstance().getConnection();

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
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lessons;
    }

    public void add(Lesson lesson) {
        String sql = "INSERT INTO lesson(name,duration,lecturer_Name,price) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setDouble(2,lesson.getDuration());
            preparedStatement.setString(3, lesson.getLecturerName());
            preparedStatement.setDouble(4, lesson.getPrice());
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
                        .lecturerName(resultSet.getString("lecturerName"))
                        .price(resultSet.getDouble("price"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}


