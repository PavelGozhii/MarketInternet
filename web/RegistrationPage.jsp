<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 03.05.2019
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div align="center">
    <h1>Registration</h1>
    <br/>
    <form action="registration" method="post">
        Login<input type="text" name="login">
        Password<input type="text" name="password">
        Email<input type="text" name="email">
        <select name="roleId" size="1">
            <option>user</option>
            <option>seller</option>
        </select>
        <input type="submit" value="Sign in">
    </form>
</div>
</body>
</html>
