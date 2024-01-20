package com.example.student_lesson.servlet;

import com.example.student_lesson.manager.LessonManager;
import com.example.student_lesson.manager.UserManager;
import com.example.student_lesson.model.Lesson;
import com.example.student_lesson.model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(urlPatterns = "/addLesson")
public class AddLessonServlet extends HttpServlet {

    private LessonManager lessonManager = new LessonManager();
    private UserManager userManager = new UserManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addLesson.jsp").forward(req,resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lessonName = req.getParameter("lessonName");
        int lessonDuration = Integer.parseInt(req.getParameter("lessonDuration"));
        String lecturerName = req.getParameter("lecturerName");
        double price = Double.parseDouble(req.getParameter("price"));
        User user =  (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        if(lessonManager.getLessonByName(lessonName) == null){
        lessonManager.add(Lesson.builder()
                        .name(lessonName)
                        .duration(lessonDuration)
                        .lecturerName(lecturerName)
                        .price(price)
                        .user(userManager.getUserById(userId))
                .build());
        resp.sendRedirect("/lessons");
        }else {
            req.getSession().setAttribute("msg", "there is a lesson with that name");
            resp.sendRedirect("/addLesson");
        }


    }
}
