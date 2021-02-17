<%@ include file="/WEB-INF/include/head.jspf"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title><fmt:message key="account_title"/> </title>
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

    <style>
        html,
        body,
        header {
            height: 60vh;
        }

        @media (max-width: 740px) {

            html,
            body,
            header {
                height: 100vh;
            }
        }

        @media (min-width:800px) and (max-width:850px) {

            html,
            body,
            header{
                height: 100vh;
            }
        }
    </style>

</head>
<body>
<c:if test="${sessionScope.loginFailed}">
    <script>alert("Wrong password");</script>
</c:if>

<jsp:include page="headeer.jsp"/>

<p class="blockText mt-5" style="font-family: Arial,serif; font-size: 30px" ><fmt:message key="account_hello"/>${sessionScope.user.username} </p>

<hr>
<main>
    <p class="blockText"><fmt:message key="account_change_username"/> </p>
    <form class="col-md-6" action="${pageContext.request.contextPath}/Servlet" method="post">
        <input type="hidden" name="command" value="changeUsernameCommand">
        <div class="col-md-6">
            <label for="username" class="col-form-label"><fmt:message key="account_new_name"/></label>
            <input type="text" class="form-control" id="username" name="username" placeholder="enter username"
                   required pattern="[\p{Alpha}\p{M}\p{Join_C}]+">
        </div>
        <div class="col-md-6 m-md-5">
            <button class="btn btn-primary btn-md my-0 p" type="submit">
                <fmt:message key="account_name_submit"/>
            </button>
        </div>
    </form>
    <hr>
    <p class="blockText"><fmt:message key="account_change_pass"/></p>
    <form class="col-md-6" action="${pageContext.request.contextPath}/Servlet" method="post">
        <input type="hidden" name="command" value="changePasswordCommand">
        <div class="col-md-6">
            <label for="oldPassword" class="col-form-label"><fmt:message key="account_current_pass"/></label>
            <input type="password" class="form-control" id="oldPassword" name="oldPassword" required>
        </div>
        <div class="col-md-6">
            <label for="newPassword" class="col-form-label"><fmt:message key="account_new_pass"/></label>
            <input type="password" class="form-control" id="newPassword" name="newPassword" required>
        </div>
        <div class="col-md-6 m-md-5">
            <button class="btn btn-primary btn-md my-0 p" type="submit">
                <fmt:message key="account_name_submit"/>
            </button>
        </div>
    </form>
    <hr>
</main>

<form action="${pageContext.request.contextPath}/Servlet" method="post">
    <input type="hidden" name="command" value="logOutCommand">
    <button type="submit" class="btn btn-primary btn-md my-0 p">
        <fmt:message key="account_Logout_button"/>
    </button>
</form>

<jsp:include page="footer.jsp"/>
<!-- jQuery -->
<script type="text/javascript" src="view/js/jquery.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="view/js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="view/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="view/js/mdb.min.js"></script>
</body>
</html>
</fmt:bundle>
<%session.removeAttribute("loginFailed");%>