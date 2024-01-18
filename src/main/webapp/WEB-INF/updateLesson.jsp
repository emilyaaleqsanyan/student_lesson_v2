<%@ page import="com.example.student_lesson.model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 16.01.2024
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Lesson</title>
</head>
<body>
<% Lesson lesson = (Lesson) request.getAttribute("lesson");%>

Update Lesson <br>
<form method="post" action="/updateLesson">
    <input type="hidden" name="lessonId" value="<%=lesson.getId()%>"><br>
    Lesson Name:<input type="text" name="lessonName" value="<%=lesson.getName()%>"><br>
    Lesson Duration:<input type="number" name="lessonDuration" value="<%=lesson.getDuration()%>"><br>
    Lecturer Name:<input type="text" name="lecturerName" value="<%=lesson.getLecturerName()%>"><br>
    Price:<input type="number" name="price" value="<%=lesson.getPrice()%>"><br>
    <input type="submit" value="update">
</form>
</body>
</html>
