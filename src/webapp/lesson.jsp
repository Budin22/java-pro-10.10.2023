<%@ page import="org.example.model.Homework" %>
<%@ page import="org.example.model.Lesson" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>ToDo app</title>
</head>
<body>
    <%
        List<Homework> homeworks = (List<Homework>) request.getAttribute("homeworks");
        Lesson lesson = (Lesson) request.getAttribute("lesson");
    %>
    <div class="container task-app">
        <div class="row">
            <a href="/budin/lessons">Back to all lessons</a>
            <h1>Lesson: <%= lesson.getName() %></h1>
            <ul class="list-group">
                <%
                    if (homeworks != null && homeworks.size() != 0) {
                        for (Homework homework: homeworks) {
                %>
                <li class="list-group-item">
                    <span><%= homework.getName() %></span>
                </li>
                <%
                        }
                    } else {
                %>
                <li class="list-group-item">
                    <span>empty</span>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>