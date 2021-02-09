<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="cst" uri="http://chantee.restaurant.com/taglib" %>
<%! final Logger LOG = LogManager.getLogger(this.getClass());%>
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
    <%LOG.debug(session.getAttribute("cart"));%>
</head>
<body>
<%@include file="headeer.jsp"%>

<main class="mt-5 pt-5" style="height: 85%">
    <div class="container dark-grey-text">
        <div class="row container display-flex justify-content-center">
               <h1 class="lable">Shopping cart</h1> 
        </div>
        <c:forEach var="cart" items="${sessionScope.cart.cart}">
            <cst:cartElement quantity="${cart.value}" imgPath="${cart.key.img_path}" title="${cart.key.title}"
                             price="${cart.key.price}" description="${cart.key.description}"/>
        </c:forEach>
    </div>


    <div class="container">
        <a href="${pageContext.request.contextPath}/order.jsp"><button type="submit">Take order</button></a>
    </div>
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
</body>
</html>