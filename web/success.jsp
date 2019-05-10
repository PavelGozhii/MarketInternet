<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 04.05.2019
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Success!</title>
</head>
<body>
<div align="center">
    <h1><c:out value="${message}"/></h1>
    <a href="${ref}">Back</a>
</div>
</body>
</html>
