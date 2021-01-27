<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% Logger LOGGER = LogManager.getLogger("com.restaurant.chantee.register-page"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Register page</title>
    <% LOGGER.info("Register page"); %>
</head>
<body>
    <form action="${pageContext.request.contextPath}/Register" method="post" accept-charset="UTF-8">
        <div class="container">
            <h1>Register</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>
            <label for="userLogin"><b>Login</b></label>
            <input type="text" placeholder="Enter your name, please" name="username" id="userLogin" required>

            <label for="userEmail"><b>Email</b></label>
            <input type="text" placeholder="Enter your email, please" name="email" id="userEmail" required>

            <label for="userPassword"><b>Password</b></label>
            <input type="password" placeholder="Enter your password, please" name="password" id="userPassword" required>
        </div>
        <div class="container signIn">
            <button type="submit">Register</button>
        </div>
    </form>
</body>
</html>
