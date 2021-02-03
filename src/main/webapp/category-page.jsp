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
        <!-- Single element -->
        <div class="row">
            <div class="col-md-6 md-4">
                <img src="view/img/goods/cakes/2cce38dd5542c967a82b3e0f32c58360.jpg"
                     alt="cake1" class="img-fluid" style="height: 300px;">
            </div>
            <div class="col-md-6 md-4">
                <div class="p-4">
                    <div class="mb-3">
                        <span class="badge purple mr-1">Some info</span>
                    </div>
                    <p class="lead">
                        <span class="mr-1">PRICE</span>
                    </p>
                    <p class="lead font-weight-bold">Decription</p>
                    <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Iure omnis, natus quia quod temporibus
                        sapiente error facilis aut voluptas. Optio architecto magni esse corrupti dolore sed non! Nulla,
                        nam vero!</p>
                    <form method="post" action="${pageContext.request.contextPath}/CartServlet" class="d-flex justify-content-left">
                        <input name="productName" type="hidden" value="tortik">
                        <input name="productQuantity" type="number" class="form-control" value="0" aria-label="Search" style="width:100px;">
                        <button type="submit" class="btn btn-primary btn-md my-0 p">
                            Add to cart <i class="fa fa-shopping-cart ml-1"></i>
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <hr>
        <!-- Single element -->
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