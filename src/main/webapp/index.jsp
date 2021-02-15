<%@ include file="/WEB-INF/include/head.jspf"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title><fmt:message key="index_title"/></title>
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
  <%@ include file="headeer.jsp"%>
    <div style="height: 50px"></div>

    <!-- Main block wrapper-->
    <main>
      <div class="begining mt-3">
        <h2>Categories</h2>
      </div>
      <section class="text-center mb-4">
        <!--Single row-->
        <div class="row wow fadeIn mt-2">
          <!--Single element-->
          <div class="col-lg-3 col-md-6 mb-4" >
            <div class="card">
              <div class="view overlay">
                <img src="view/img/goods/cakes/category.jpg" alt="Cake" class="card-img-top">
                <form method="post" action="${pageContext.request.contextPath}/Servlet">
                  <input type="hidden" name="command" value="generateProductList">
                  <input type="hidden" name="category" value="cakes">
                  <input type="submit" value="Cakes">
                </form>
              </div>
            </div>
          </div>
          <!--Single element-->
        </div>
        <!--Single row-->
      </section>
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