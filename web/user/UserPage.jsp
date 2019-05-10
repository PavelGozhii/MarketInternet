<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 03.05.2019
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserPage</title>
</head>
<body>
<div align="center">
    <h1>Hello, user <c:out value="${login}"/>!</h1>
    <br/>
    <table border="1" cellpadding="5">
        <caption><h2>List of Goods</h2></caption>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Owner</th>
            <th>Price</th>
        </tr>
        <c:forEach var="good" items="${goodsList}">
            <tr>
                <td><c:out value="${good.name}"/></td>
                <td><c:out value="${good.description}"/></td>
                <td><c:out value="${good.owner}"/></td>
                <td><c:out value="${good.price}"/></td>
                <td>
                    <a href="buy?id=<c:out value='${good.id}'/> ">Buy</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/logout">Log out</a>
</div>
</body>
</html>
