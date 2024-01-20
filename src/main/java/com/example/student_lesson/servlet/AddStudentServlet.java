package com.example.student_lesson.servlet;

import com.example.student_lesson.manager.LessonManager;
import com.example.student_lesson.manager.StudentManager;
import com.example.student_lesson.manager.UserManager;
import com.example.student_lesson.model.Lesson;
import com.example.student_lesson.model.Student;
import com.example.student_lesson.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/addStudent")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1
)
public class AddStudentServlet extends HttpServlet {


    private final LessonManager lessonManager = new LessonManager();
    private final StudentManager studentManager = new StudentManager();
    private final String UPLOAD_DIRECTORY = "C:\\Users\\Lenovo\\ee\\student_lesson\\uploadDirectory";

    private static UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons = lessonManager.getAllLessons();
        req.setAttribute("lessons", lessons);
        req.getRequestDispatcher("/WEB-INF/addStudent.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));
        int lessonId = Integer.parseInt(req.getParameter("lessonId"));

        Part picture = req.getPart("picture");
        String pictureName = null;
        if (picture != null && picture.getSize() > 0) {
            pictureName = System.currentTimeMillis() + "_" + picture.getSubmittedFileName();
            picture.write(UPLOAD_DIRECTORY + File.separator + pictureName);
        }
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        if (studentManager.getStudentByEmail(email) == null) {
            studentManager.add(Student.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .picName(pictureName)
                    .age(age)
                    .lesson(lessonManager.getLessonById(lessonId))
                    .user(userManager.getUserById(userId))
                    .build());
            resp.sendRedirect("/students");
        } else {
            req.getSession().setAttribute("msg", "a student with this email already exists");
            resp.sendRedirect("/addStudent");
        }

    }

}


