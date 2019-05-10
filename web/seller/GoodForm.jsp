<%--
  Created by IntelliJ IDEA.
  User: Павел
  Date: 04.05.2019
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>GoodDao</title>
</head>
<body>
<div align="center">
    <h1>GoodDao</h1>
    <h2>
        <a href="new">Add new good</a>
        &nbsp;&nbsp;&nbsp;
        <a href="home">List all goods</a>
    </h2>
</div>
<div align="center">
    <c:if test="${good != null}">
    <form action="edit" method="post">
        </c:if>
        <c:if test="${godd == null}">
        <form action="create" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${good != null}">
                            Edit good
                        </c:if>
                        <c:if test="${good == null}">
                            Add new good
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${good!= null}">
                    <input type="hidden" name="id"
                           value="<c:out value='${good.id}'/>"
                    />
                </c:if>
                <tr>
                    <th>Name</th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${good.name}'/>"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Description</th>
                    <td>
                        <input type="text" name="description"
                               value="<c:out value='${good.description}'/>"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Price</th>
                    <td>
                        <input type="text" name="price"
                               value="<c:out value='${good.price}'/> "
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
    </form>
</div>
</body>
</html>
