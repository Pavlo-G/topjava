<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 06.06.2020
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>


<table class="table table-bordered table-striped">
    <thead class="thead-dark">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Date and Time</th>
        <th scope="col">Description</th>
        <th scope="col">Calories</th>
        <th scope="col">Excess</th>
        <th scope="col">Update</th>
        <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>

    <jsp:useBean id="mealsList" scope="request"  type="java.util.List"/>
    <c:forEach var="meal" items="${mealsList}">


        <tr style="color:${meal.excess ? 'red' : 'green'}">
            <td><c:out value="${meal.id}"/></td>
            <td>
                <tags:localDate date="${meal.dateTime}"/>
            </td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><c:out value="${meal.excess}"/></td>

            <td>
                <form action="${pageContext.request.contextPath}/meals" method="post">
                    <input type="hidden" name="id" value="${meal.id}">
                <button name="buttonFindById"  class="btn btn-warning">Update</button>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/meals" method="post">
                    <input type="hidden" name="id" value="${meal.id}">
                    <button name="buttonDelete" type="submit" value="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>

<form hidden="hidden" name="formUpdate" action="${pageContext.request.contextPath}/meals" method="post">
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Date and Time</th>
            <th scope="col">Description</th>
            <th scope="col">Calories</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="datetime-local" name="dateTime"><br><br></td>
            <td><input type="text" name="description"><br><br></td>
            <td><input name="calories"><br><br></td>
            <td>
                <button name="buttonUpdate" type="submit" value="submit" class="btn btn-warning">Update</button>
            </td>
        </tr>
        </tbody>
    </table>
</form>


<form name="formAdd" action="${pageContext.request.contextPath}/meals" method="post">
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Date and Time</th>
            <th scope="col">Description</th>
            <th scope="col">Calories</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="datetime-local" name="dateTime"><br><br></td>
            <td><input type="text" name="description"><br><br></td>
            <td><input type="text" name="calories"><br><br></td>
            <td>
                <button name="buttonAdd" type="submit" value="submit" class="btn btn-danger">Create</button>
            </td>

        </tr>
        </tbody>
    </table>
</form>

<form name="formFindById" action="${pageContext.request.contextPath}/meals" method="post">
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="text" name="id"><br><br></td>
            <td>
                <button name="buttonFindById" type="submit" value="submit" class="btn btn-danger">FindById</button>
            </td>

        </tr>
        </tbody>
    </table>
</form>


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
