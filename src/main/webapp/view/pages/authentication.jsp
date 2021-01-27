
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Authentication</title>
</head>
<body>
<h1>Hello, anonymous, let`s sign in</h1>
<form action="${pageContext.request.contextPath}/Authentication" method="post">
    <div class="container">
        <label for="userEmail"><b>Email</b></label>
        <input type="text" placeholder="Enter your email, please" name="email" id="userEmail" required>

        <label for="userPassword"><b>Password</b></label>
        <input type="password" placeholder="Enter your password, please" name="password" id="userPassword" required>
    </div>
    <div class="container signIn">
        <button type="submit">Sign in</button>
    </div>
</form>
</body>
</html>
