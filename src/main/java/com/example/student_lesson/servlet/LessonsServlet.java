package com.example.student_lesson.servlet;

import com.example.student_lesson.manager.LessonManager;
import com.example.student_lesson.model.Lesson;
import com.example.student_lesson.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/lessons")
public class LessonsServlet extends HttpServlet {


 private LessonManager lessonManager = new LessonManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        int userId = user.getId();

        List<Lesson> lessons = lessonManager.getAllLessonsByUserId(userId);

        req.setAttribute("lessons", lessons);

        req.getRequestDispatcher("/WEB-INF/lessons.jsp").forward(req,resp);
    }
}
