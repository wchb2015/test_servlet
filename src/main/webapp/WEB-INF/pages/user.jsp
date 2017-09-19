<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <br><br>
    <br><br>
    <table>
        <tr>
            <td>
                <table border="1" cellpadding="10" cellspacing="0">

                    <c:forEach items="${users }" var="user">

                        <tr>
                            <td>${user.userName }</td>
                            <td>${user.email }</td>
                            <td>${user.age }</td>
                        </tr>

                    </c:forEach>

                </table>
                <br><br>
            </td>
        </tr>

    </table>

</center>

</body>
</html>
