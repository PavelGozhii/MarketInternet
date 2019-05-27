<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 04.05.2019
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Purchase</title>
</head>
<body>
<div align="center">
    <h1>Your purchase, <c:out value="${login}"/></h1>
    <h2>To buy this good you need confirm it, we send code in your email</h2>
    <table border="1" cellpadding="5">
        <caption><h2>List of Goods</h2></caption>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Owner</th>
            <th>Price</th>
        </tr>
        <c:forEach var="good" items="${goods}">
            <tr>
                <td><c:out value="${good.name}"/></td>
                <td><c:out value="${good.description}"/></td>
                <td><c:out value="${good.owner}"/></td>
                <td><c:out value="${good.price}"/></td>
            </tr>
        </c:forEach>
    </table>
    <form action="buy" method="post">
        Code <input type="text" name="code"/>
        <input type="submit" value="Confirm"/>
    </form>
</div>
</body>
</html>
