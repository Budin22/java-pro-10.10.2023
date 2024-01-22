<%@ page import="ua.ithillel.jee.model.Task" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">

    <title>ToDo app</title>
</head>
<body>

    <%
        final List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    %>

    <div class="container task-app">
        <form class="row" method="post" action="task">
            <div class="col-5">
                <div class="mb-3">
                    <input type="text" class="form-control" id="todo" name="todo">
                </div>
            </div>
            <div class="col-1">
                <button type="submit" class="btn btn-primary">Add</button>
            </div>

        </form>
        <div class="row">
            <form class="col-6" action="task" method="post">
                <ul class="list-group">
                    <%
                        if (tasks != null) {
                            for (Task task: tasks) {
                    %>
                    <li class="list-group-item">
                        <input type="checkbox"
                               name="status"
                               value="<%= task.isStatus() %>">
                        <span>
                            <%= task.getName() %>
                        </span>
                    </li>
                    <%
                            } // end for
                        } // end if
                    %>
                </ul>
            </form>
        </div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>