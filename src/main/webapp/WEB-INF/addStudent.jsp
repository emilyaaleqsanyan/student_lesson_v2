<%@ page import="com.example.student_lesson.model.Lesson" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12.01.2024
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>

<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>

Add Student <br>
<form method="post" action="/addStudent" enctype="multipart/form-data">
    Name:<input type="text" name="name"><br>
    Surname:<input type="text" name="surname"><br>
    Email:<input type="text" name="email"><br>
    Age:<input type="number" name="age"><br>

    <select name="lessonId">
        <%
            for (Lesson lesson : lessons) { %>
        <option value="<%=lesson.getId()%>"><%=lesson.getName()%></option>
        <% }%>

    </select><br>
    <input type="file" name="picture"><br>

    <input type="submit" value="add">
</form>
</body>
</html>
