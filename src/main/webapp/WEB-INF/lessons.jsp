<%@ page import="java.util.List" %>
<%@ page import="com.example.student_lesson.model.Lesson" %>
<%@ page import="com.example.student_lesson.manager.UserManager" %>
<%@ page import="com.example.student_lesson.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12.01.2024
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Lessons</title>
</head>
<body>

<%
    List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");
    User user =(User) session.getAttribute("user");
%>

Lessons | <a href="/addLesson">Add Lesson</a>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Duration</th>
        <th>LecturerName</th>
        <th>Price</th>
        <th>UserId</th>
        <th>Delete</th>
        <th>Update</th>
    </tr>

    <%
        if (!lessons.isEmpty()) {
            for (Lesson lesson : lessons) { %>

    <tr>
        <td><%=lesson.getId()%>
        </td>
        <td><a href="/singleLesson?id=<%=lesson.getId()%>"><%=lesson.getName()%></a>
        </td>
        <td><%=lesson.getDuration()%>
        </td>
        <td><%=lesson.getLecturerName()%>
        </td>
        <td><%=lesson.getPrice()%>
        </td>
        <td><%=user.getId()%>

        </td>
        <td><a href="/deleteLesson?id=<%=lesson.getId()%>">delete</a></td>
        <td><a href="/updateLesson?id=<%=lesson.getId()%>">update</a> </td>
    </tr>
    <%}
    %>
</table>
<%}%>
</body>
</html>
