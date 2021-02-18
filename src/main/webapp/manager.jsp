<%@ include file="/WEB-INF/include/head.jspf"%>
<%@taglib prefix="cst" uri="http://chantee.restaurant.com/taglib" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">
<html>
<head>
    <title><fmt:message key="manager_title"/> </title>
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
        .order {
            overflow: scroll;
            width: 350px;
            height: 500px;
            padding: 5px;
            border: solid 1px black;
        }
    </style>
</head>
<body>
<jsp:include page="headeer.jsp"/>
<div class="mt-5">
    <hr>
</div>
<main class="begining mt-5">
    <h2>Manager-panel</h2>
    <hr>
    <h3>Manager ${sessionScope.user.username}</h3>
    <form action="${pageContext.request.contextPath}/Servlet" method="post" class="ml-5">
        <input type="hidden" name="command" value="prepareManagerData">
        <button class="btn btn-primary btn-md my-0 p" type="submit">
            <fmt:message key="manager_refresh_btn"/>
        </button>
    </form>
    <section class="text-center mb-4 justify-content-around">
        <!--Single row-->
        <div class="row mt-2">
            <!--Single element-->
            <div class="col-lg-3 col-md-6 mb-4">
                <h3><fmt:message key="manager_ordered_header"/> </h3>
                <div class="order">
                <c:forEach var="order" items="${sessionScope.ordered}">
                    <cst:Order order_id="${order.id}" cart="${order.cart}" dateTime="${order.dateTime}"
                               delivery_address="${order.delivery_address}" phone="${order.phone}" comment="${order.comment}"/>
                    <cst:OrderedButtons order_id="${order.id}"/>
                    <hr>
                </c:forEach>
                </div>
            </div>
            <!--Single element-->
            <div class="col-lg-3 col-md-6 mb-4 ml-4" >
                <h3><fmt:message key="manager_kitchen_header"/> </h3>
                <div class="order">
                <c:forEach var="order" items="${sessionScope.kitchen}">
                    <cst:Order order_id="${order.id}" cart="${order.cart}" dateTime="${order.dateTime}"
                               delivery_address="${order.delivery_address}" phone="${order.phone}" comment="${order.comment}"/>
                    <cst:KitchenButtons order_id="${order.id}"/>
                    <hr>
                </c:forEach>
                </div>
            </div>
            <!--Single element-->
            <div class="col-lg-3 col-md-6 mb-4 ml-4" >
                <h3><fmt:message key="manager_delivery_header"/></h3>
                <div class="order">
                <c:forEach var="order" items="${sessionScope.delivery}">
                    <cst:Order order_id="${order.id}" cart="${order.cart}" dateTime="${order.dateTime}"
                               delivery_address="${order.delivery_address}" phone="${order.phone}" comment="${order.comment}"/>
                    <cst:DeliveryButtons order_id="${order.id}"/>
                    <hr>
                </c:forEach>
                </div>
            </div>
            <!--Single element-->
        </div>
        <!--Single row-->
    </section>
    <form action="${pageContext.request.contextPath}/Servlet" method="post">
        <input type="hidden" name="command" value="logOutCommand">
        <button class="btn btn-primary btn-md my-0 p" type="submit">
            <fmt:message key="manager_logout_button"/>
        </button>
    </form>
</main>

</body>
</html>
</fmt:bundle>