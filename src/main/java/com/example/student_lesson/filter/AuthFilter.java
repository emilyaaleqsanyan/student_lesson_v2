package com.example.student_lesson.filter;

import com.example.student_lesson.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/home", "/lesson", "/student", "/singleLesson",
        "/addLesson", "/addStudent", "/deleteStudent",
        "/deleteLesson", "/update", "/logout"
})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/");
        }
    }
}
