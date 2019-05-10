<%--
Created by IntelliJ IDEA.
User: Павел
Date: 01.05.2019
Time: 22:29
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<div align="center">
    <h1>Welcome</h1>
    <c:if test="${login != null}">
        You have been registered, <c:out value="${login}"/>
        <br/>
    </c:if>
    <form action="login" method="post">
        Login<input type="text" name="login"/>
        <br/>
        Password<input type="text" name="password"/>
        <br/>
        <input value="Log in" type="submit"/>
    </form>
    <br/>
    <a href="RegistrationPage.jsp">Is not yet registered?</a>
</div>
</body>
</html>
