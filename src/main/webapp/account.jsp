<%@ include file="/WEB-INF/include/head.jspf"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><fmt:message key="account_title"/> </title>
</head>
<body>
Customer
<form action="${pageContext.request.contextPath}/Servlet" method="post">
    <input type="hidden" name="command" value="logOutCommand">
    <button type="submit">
        <fmt:message key="account_Logout_button"/>
    </button>
</form>
</body>
</html>
</fmt:bundle>