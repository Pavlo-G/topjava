<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 08.06.2020
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Element By Id</title>
</head>
<body>
<jsp:useBean id="elementById" scope="request" type="ru.javawebinar.topjava.model.Meal"/>

<table class="table table-bordered table-striped">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Date and Time</th>
        <th scope="col">Description</th>
        <th scope="col">Calories</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${elementById.id}</td>
        <td><tags:localDate date="${elementById.dateTime}"/></td>
        <td>${elementById.description}</td>
        <td>${elementById.calories}</td>
        <td>

        </td>
    </tr>
    <form  name="formUpdate" action="${pageContext.request.contextPath}/meals" method="post">
        <tr>

            <td> <input  type="hidden" name="id" value= "${elementById.id}"/><br><br></td>
            <td><label>
                <input type="datetime-local" name="dateTime" value="${elementById.dateTime}"/>
            </label><br><br></td>
            <td><input type="text" name="description" value="${elementById.description}"><br><br></td>
            <td><input name="calories" value="${elementById.calories}"><br><br></td>
            <td>
                <button name="buttonUpdate" type="submit" value="submit" class="btn btn-warning">Update</button>
            </td>

        </tr>
    </form>
    </tbody>
</table>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
