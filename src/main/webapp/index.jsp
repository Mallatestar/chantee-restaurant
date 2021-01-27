<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page import="com.restaurant.chantee.model.domain.entity.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% Logger LOGGER = LogManager.getLogger("com.restaurant.chantee.chantee_restaurant"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Chantee restaurant</title>
    <% LOGGER.info("MainPAge"); %>
    <%String username = "Anonymous";
        User user = (User) session.getAttribute("user");
        if (user != null){username = user.getUsername();}%>
</head>
<body>
<h1>Hello, <%=username%></h1><br/>
<h1>Please, sign in or create a new account</h1><br/>
<a href="${pageContext.request.contextPath}/Register">Register</a>
<a href="${pageContext.request.contextPath}/Authentication">Sign in</a>
</body>
</html>