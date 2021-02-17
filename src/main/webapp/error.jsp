<%@ include file="/WEB-INF/include/head.jspf"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">
    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="view/img/mdb-favicon.ico" type="image/x-icon">
        <title>Error</title>
        <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/cover/">
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="view/css/bootstrap.min.css">
        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="view/css/cover.css">
    </head>

    <body class="text-center">

    <div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
        <main role="main" class="inner cover">
            <h1 class="cover-heading"><fmt:message key="error_oops"/> </h1>
            <p class="lead"><fmt:message key="error_sorry"/> </p>
            <p class="lead"><fmt:message key="error_thanks"/> </p>
            <p class="lead"><a class="btn btn-lg btn-secondary" href="${pageContext.request.contextPath}/home"><fmt:message key="error_return"/></a> </p>

        </main>

    </div>

    <!-- jQuery -->
    <script type="text/javascript" src="view/js/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="view/js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="view/js/bootstrap.min.js"></script>
    </body>
    </html>
    </fmt:bundle>