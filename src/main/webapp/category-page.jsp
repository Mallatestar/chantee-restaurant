<%@ include file="/WEB-INF/include/head.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="cst" uri="http://chantee.restaurant.com/taglib" %>
<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%! final Logger LOG = LogManager.getLogger(this.getClass());%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title><fmt:message key="category_page_title"/> </title>
    <%LOG.debug(session.getAttribute("products"));%>
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
<hr>
<div class="container mt-5 row">
    <div class="col">
        <p><fmt:message key="category_page_sorter"/> </p>
    </div>
    <div class="col">
        <form action="${pageContext.request.contextPath}/Servlet" method="post">
            <input type="hidden" name="command" value="generateProductList">
            <input type="hidden" name="category" value="${sessionScope.category}">
            <input type="hidden" name="sortParam" value="byName">
            <button type="submit"><fmt:message key="category_page_byName"/></button>
        </form>
    </div>
    <div class="col">
        <form action="${pageContext.request.contextPath}/Servlet" method="post">
            <input type="hidden" name="command" value="generateProductList">
            <input type="hidden" name="category" value="${sessionScope.category}">
            <input type="hidden" name="sortParam" value="byPriceDescending">
            <button type="submit"><fmt:message key="category_page_byPriceDescending"/></button>
        </form>
    </div>
    <div class="col">
        <form action="${pageContext.request.contextPath}/Servlet" method="post">
            <input type="hidden" name="command" value="generateProductList">
            <input type="hidden" name="category" value="${sessionScope.category}">
            <input type="hidden" name="sortParam" value="byPriceAscending">
            <button type="submit"><fmt:message key="category_page_byPriceAscending"/></button>
        </form>
    </div>
</div>
<hr>

<!-- Main block wrapper-->
<main class="mt-5 pt-4">
    <div class="container dark-grey-text mt-5">
        <c:forEach var="product" items="${sessionScope.productMap.get(sessionScope.categoryPageNumber)}">
           <cst:product imgPath="${product.img_path}" title="${product.title}" price="${product.price}" description="${product.description}"/>
        </c:forEach>
    </div>

    <!--PAGINATION -->
    <nav class="d-flex justify-content-center">
        <ul class="pagination pg-blue">
            <c:if test="${sessionScope.categoryPageNumber != 1}">
            <li class="page-item">
                <form action="${pageContext.request.contextPath}/Servlet" method="post">
                    <input type="hidden" name="command" value="previousPageCommand">
                    <button type="submit">
                        <span aria-hidden="true">&laquo;</span>
                    </button>
                </form>
            </li>
            </c:if>
            <c:if test="${sessionScope.categoryPageNumber != sessionScope.productMap.keySet().size()}">
            <li class="page-item">
                <form action="${pageContext.request.contextPath}/Servlet" method="post">
                    <input type="hidden" name="command" value="nextPageCommand">
                    <button type="submit">
                        <span aria-hidden="true">&raquo;</span>
                    </button>
                </form>
            </li>
            </c:if>
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
    </fmt:bundle>