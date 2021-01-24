<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% Logger LOGGER = LogManager.getLogger("com.restaurant.chantee.chantee_restaurant"); %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <% LOGGER.info("MainPAge"); %>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="Servlet">Hello Servlet</a>
</body>
</html>