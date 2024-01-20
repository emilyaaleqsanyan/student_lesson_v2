<%@ page import="com.example.student_lesson.model.Lesson" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.student_lesson.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 18.01.2024
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Lesson lesson = (Lesson) request.getAttribute("lesson");%>
<% List< Student> studentList = (List<Student>)request.getAttribute("students"); %>
<html>
<head>
    <title><%=lesson.getName()%>></title>
</head>
<body>

<h2><%=lesson.getName()%> | <%=lesson.getId()%></h2>
Duration: <span><%=lesson.getDuration()%></span><br>

Students:
<%
    if (studentList != null && !studentList.isEmpty()) {%>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Picture</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Age</th>

    </tr>
        <%
        for (Student student : studentList) { %>
    <tr>
        <td><%=student.getId()%>
        </td>
        <td>
            <% if (student.getPicName() != null) {%>
            <img src="/downloadImage?imageName=<%=student.getPicName()%>" width="50">
            <%} else {%>
            <span>No Picture</span>
            <%}%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getSurname()%>
        </td>
        <td><%=student.getEmail()%>
        </td>
        <td><%=student.getAge()%>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%}%>



</body>
</html>
