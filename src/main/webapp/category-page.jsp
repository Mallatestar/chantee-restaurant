<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="test" uri="http://tomcat.apache.org/example-taglib" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Material Design for Bootstrap</title>
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

        @media (min-width: 800px) and (max-width: 850px) {

            html,
            body,
            header {
                height: 100vh;
            }
        }
    </style>
</head>

<body>
<%@include file="headeer.jsp"%>


<!-- Main block wrapper-->
<main class="mt-5 pt-4">
    <div class="container dark-grey-text mt-5">
        <jsp:useBean id="products" scope="request" type="java.util.List"/>
        <c:forEach var="product" items="${products}">
            <test:product
                    name = "${product.name}"
                    imgPath="${product.imgPath}"
                    price="${product.price}"
                    description="${product.description}"
            />
        </c:forEach>
    </div>


    <!--PAGINATION -->
    <nav class="d-flex justify-content-center">
        <ul class="pagination pg-blue">
            <li class="page-item disabled">
                <a href="#" class="page-link" aria-label="Previous">
          <span aria-hidden="true">
            &laquo;
          </span>
                </a>
            </li>
            <li class="page-item">
                <a href="#" class="page-link" aria-label="Next">
          <span aria-hidden="true">
            &raquo;
          </span>
                </a>
            </li>
        </ul>
    </nav>
    <!--PAGINATION -->
</main>
<!-- Main block wrapper-->


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