<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 03.05.2019
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<div align="center">
    <h1>Error</h1>
    <h2><c:out value="${error}"/></h2>
    <a href="${ref}">Back</a>
</div>
</body>
</html>