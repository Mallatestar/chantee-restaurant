<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- MDB icon -->
    <link rel="icon" href="view/img/mdb-favicon.ico" type="image/x-icon">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Google Fonts Roboto -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="view/css/bootstrap.min.css">
    <!-- Material Design Bootstrap -->
    <link rel="stylesheet" href="view/css/mdb.min.css">
    <!-- Your custom styles (optional) -->
    <link rel="stylesheet" href="view/css/style.css">
    <title>Document</title>
</head>

<body>
    <%@include file="headeer.jsp"%>

    <main class="mt-5 pt-5">
        <form class="col-md-6" action="${pageContext.request.contextPath}/Servlet" method="post">
            <input type="hidden" name="command" value="loginCommand">
            <div class="row g-3">
                <h2>Sign in</h2>
            </div>
            <div class="col-md-6">
                <label for="email" class="col-form-label">Email</label>
                <input type="text" class="form-control" id="email" name="email" placeholder="enter email" required>
            </div>
            <div class="col-md-6">
                <label for="user_password" class="col-form-label">Username</label>
                <input type="password" class="form-control" id="user_password" name="user_password" placeholder="enter password" required>
            </div>
            <div class="col-md-6 m-md-5">
                <button type="submit">
                    Log in
                </button>             
            </div>
            <div class="col-md-6">
                <p>If you have no account, you can create a new one <a href="${pageContext.request.contextPath}/register.jsp">here.</a></p>
            </div>
        </form>
    </main>

    <%@include file="footer.jsp"%>
    <!-- jQuery -->
    <script type="text/javascript" src="view/js/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="view/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="view/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="view/js/mdb.min.js"></script>

    <script type="text/javascript" src="view/js/myscript.js"></script>

</body>
</html>