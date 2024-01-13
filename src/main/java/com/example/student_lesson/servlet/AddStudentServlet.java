package com.example.student_lesson.servlet;

import com.example.student_lesson.manager.LessonManager;
import com.example.student_lesson.manager.StudentManager;
import com.example.student_lesson.model.Lesson;
import com.example.student_lesson.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/addStudent")
public class AddStudentServlet extends HttpServlet {


    private final LessonManager lessonManager = new LessonManager();
    private final StudentManager studentManager = new StudentManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonManager.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        int lessonId = Integer.parseInt(req.getParameter("lessonId"));
        studentManager.add(Student.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .age(age)
                        .lesson(lessonManager.getLessonById(lessonId))
                .build());
        resp.sendRedirect("/students");


    }
}
