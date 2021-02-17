<%@ include file="/WEB-INF/include/head.jspf"%>
<%@taglib prefix="cst" uri="http://chantee.restaurant.com/taglib" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Google Fonts Roboto -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="view/css/bootstrap.min.css">
    <!-- Material Design Bootstrap -->
    <link rel="stylesheet" href="view/css/mdb.min.css">
    <!-- Your custom styles (optional) -->
    <link rel="stylesheet" href="view/css/style.css">
</head>
<body>
Manager
<form action="${pageContext.request.contextPath}/Servlet" method="post">
    <input type="hidden" name="command" value="logOutCommand">
    <button type="submit">
        Logout
    </button>
</form>

<main>
    <div class="begining mt-3">
        <h2>Orders</h2>
    </div>

    <form action="${pageContext.request.contextPath}/Servlet" method="post">
        <input type="hidden" name="command" value="prepareManagerData">
        <button type="submit">Refresh data</button>
    </form>
    <section class="text-center mb-4">
        <!--Single row-->
        <div class="row mt-2">
            <!--Single element-->
            <div class="col-lg-3 col-md-6 mb-4">
                <h3>Ordered</h3>
                <c:forEach var="order" items="${sessionScope.ordered}">
                    <cst:Order order_id="${order.id}" cart="${order.cart}" dateTime="${order.dateTime}"
                               delivery_address="${order.delivery_address}" phone="${order.phone}" comment="${order.comment}"/>
                    <cst:OrderedButtons order_id="${order.id}"/>
                    <hr>
                </c:forEach>
            </div>
            <!--Single element-->
            <div class="col-lg-3 col-md-6 mb-4" >
                <h3>Kitchen</h3>
                <c:forEach var="order" items="${sessionScope.kitchen}">
                    <cst:Order order_id="${order.id}" cart="${order.cart}" dateTime="${order.dateTime}"
                               delivery_address="${order.delivery_address}" phone="${order.phone}" comment="${order.comment}"/>
                    <cst:KitchenButtons order_id="${order.id}"/>
                    <hr>
                </c:forEach>
            </div>
            <!--Single element-->
            <div class="col-lg-3 col-md-6 mb-4" >
                <h3>Delivery</h3>
                <c:forEach var="order" items="${sessionScope.delivery}">
                    <cst:Order order_id="${order.id}" cart="${order.cart}" dateTime="${order.dateTime}"
                               delivery_address="${order.delivery_address}" phone="${order.phone}" comment="${order.comment}"/>
                    <cst:DeliveryButtons order_id="${order.id}"/>
                    <hr>
                </c:forEach>
            </div>
            <!--Single element-->

        </div>
        <!--Single row-->
    </section>
</main>

</body>
</html>
</fmt:bundle>