<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 03.05.2019
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SellerPage</title>
</head>
<body>
<div align="center">
    <h1>Seller <c:out value="${login}"/></h1>
    <h2>
        <a href="create">Add new good</a>
        &nbsp; &nbsp; &nbsp;
        <a href="home">List All goods</a>
    </h2>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of your goods</h2></caption>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
        </tr>
        <c:forEach var="good" items="${goodsList}">
            <tr>
                <td><c:out value="${good.name}"/></td>
                <td><c:out value="${good.description}"/></td>
                <td><c:out value="${good.price}"/></td>
                <td>
                    <a href="edit?id=<c:out value='${good.id}'/>">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=<c:out value='${good.id}'/>">Delete</a>

                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/logout">Log out</a>
</div>
</body>
</html>
