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
<jsp:include page="headeer.jsp"/>
<form action="${pageContext.request.contextPath}/Servlet" method="post">
    <input type="hidden" name="command" value="logOutCommand">
    <button type="submit">
        <fmt:message key="account_Logout_button"/>
    </button>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>
</fmt:bundle>