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
    <h2>Good: <c:out value="${good.name}"/></h2><br/>
    <h2>Description: <c:out value="${good.description}"/></h2><br/>
    <h2>Price: <c:out value="${good.price}"/></h2>
    <form action="buy" method="post">
        Code <input type="text" name="code"/>
        <input type="submit" value="Confirm"/>
    </form>
</div>
</body>
</html>
