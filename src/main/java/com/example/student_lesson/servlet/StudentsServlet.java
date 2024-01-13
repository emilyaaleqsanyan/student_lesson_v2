package com.example.student_lesson.servlet;

import com.example.student_lesson.manager.StudentManager;
import com.example.student_lesson.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/students")
public class StudentsServlet extends HttpServlet {

private StudentManager studentManager = new StudentManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentManager.getAllStudents();
        req.setAttribute("students", students);

        req.getRequestDispatcher("/WEB-INF/students.jsp").forward(req,resp);
    }
}
