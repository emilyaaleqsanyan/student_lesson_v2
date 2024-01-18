package com.example.student_lesson.manager;

import com.example.student_lesson.db.DBConnectionProvider;
import com.example.student_lesson.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private LessonManager lessonManager = new LessonManager();


    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                students.add(Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .picName(resultSet.getString("pic_Name"))
                        .lesson(lessonManager.getLessonById(resultSet.getInt("lesson_id")))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }


    public void add(Student student) {
        String sql = "INSERT INTO student(name,surname,email,age,lesson_id,pic_Name) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.setString(6, student.getPicName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                student.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void delete(int id) {
        String sql = "DELETE FROM student WHERE id = " + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Student> getByLessonId(int lessonId) {
        String sql = "SELECT * FROM student WHERE lesson_id = " + lessonId;
        List<Student> students = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                students.add(Student.builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .surname(resultSet.getString("surname"))
                                .email(resultSet.getString("email"))
                                .age(resultSet.getInt("age"))
                                .lesson(lessonManager.getLessonById(resultSet.getInt("lesson_id")))
                                .picName(resultSet.getString("pic_Name"))
                        .build());
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return students;
    }
}
