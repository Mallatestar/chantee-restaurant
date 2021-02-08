<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.logging.log4j.Logger" %>
<%@ page import="org.apache.logging.log4j.LogManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! final Logger LOG = LogManager.getLogger(this.getClass());%>
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
    header,
    .carousel {
      height: 60vh;
    }

    @media (max-width: 740px) {

      html,
      body,
      header,
      .carousel {
        height: 100vh;
      }
    }

    @media (min-width:800px) and (max-width:850px) {

      html,
      body,
      header,
      .carousel {
        height: 100vh;
      }
    }
  </style>


</head>

<body>
  <%@ include file="headeer.jsp"%>



  <!--TODO: Carousel not sliding!!!-->
  <!-- Carousel wrapper -->
  <div id="carouselBasicExample" class="carousel slide carousel-fade" data-mdb-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-mdb-target="#carouselBasicExample" data-mdb-slide-to="0" class="active"></li>
      <li data-mdb-target="#carouselBasicExample" data-mdb-slide-to="1"></li>
      <li data-mdb-target="#carouselBasicExample" data-mdb-slide-to="2"></li>
    </ol>

    <!-- Inner -->
    <div class="carousel-inner">
      <!-- Single item -->
      <div class="carousel-item active" data-mdb="0">
        <img src="https://mdbootstrap.com/img/Photos/Slides/img%20(15).jpg" height="450" class="d-block w-100"
          alt="..." />
        <div class="carousel-caption d-none d-md-block">
          <h5>First slide label</h5>
          <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
        </div>
      </div>
      <!-- Single item -->
      <div class="carousel-item active">
        <img src="https://mdbootstrap.com/img/Photos/Slides/img%20(15).jpg" height="450" class="d-block w-100"
          alt="..." />
        <div class="carousel-caption d-none d-md-block">
          <h5>Second slide label</h5>
          <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
        </div>
      </div>
      <!-- Single item -->
      <div class="carousel-item active">
        <img src="https://mdbootstrap.com/img/Photos/Slides/img%20(15).jpg" height="450" class="d-block w-100"
          alt="..." />
        <div class="carousel-caption d-none d-md-block">
          <h5>Third slide label</h5>
          <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
        </div>

      </div>
      <!-- Inner -->
    </div>
    <!-- Carousel wrapper -->
  </div>


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


  <c:if test="${sessionScope.user != null}">
    <script>alert('Hello, ${sessionScope.user.username} !');</script>
  </c:if>

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