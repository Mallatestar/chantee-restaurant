<%--
  Created by IntelliJ IDEA.
  User: Mallatesta
  Date: 2/17/2021
  Time: 11:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/Servlet">
    <input type="hidden" name="command" value="makeReturn">
    <input type="hidden" name="jspName" value="/test.jsp">
    <button type="submit">Submit</button>
</form>
</body>
</html>
