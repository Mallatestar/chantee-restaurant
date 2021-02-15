<%@ include file="/WEB-INF/include/head.jspf"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">
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
    <title><fmt:message key="order_title"/></title>
</head>

<body>
    <%@include file="headeer.jsp"%>

    <main class="mt-5 pt-5" style="height: 85%">
        <form class="row g-3 needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/Servlet">
            <input type="hidden" name="command" value="takeOrderCommand">
            <div class="col-md-4">
                <div class="form-outline">
                    <label for="phone" class="form-label"><fmt:message key="order_label_phone"/></label>
                    <input type="text" class="form-control" id="phone" name="phone" required />
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-outline">
                    <label for="address" class="form-label"><fmt:message key="order_label_address"/></label>
                    <input type="text" class="form-control" id="address" name="address" required />
                </div>
            </div>
            <div class="col-md-12">
                <div class="form-outline">
                    <label for="comment" class="form-label"><fmt:message key="order_label_com"/></label>
                    <input type="text" class="form-control" id="comment" name="comment"/>
                </div>
            </div>
            <div class="col-12">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required />
                    <label class="form-check-label" for="invalidCheck">
                        <fmt:message key="order_checkbox"/>
                    </label>
                    <div class="invalid-feedback">You must agree before submitting.</div>
                </div>
            </div>
            <div class="col-12">
                <button class="btn btn-primary" type="submit"><fmt:message key="order_submit"/></button>
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
</fmt:bundle>