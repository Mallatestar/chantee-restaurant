<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Material Design for Bootstrap</title>
    <!-- MDB icon -->
    <link rel="icon" href="img/mdb-favicon.ico" type="image/x-icon">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Google Fonts Roboto -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Material Design Bootstrap -->
    <link rel="stylesheet" href="css/mdb.min.css">
    <!-- Your custom styles (optional) -->
    <link rel="stylesheet" href="css/style.css">

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
<!-- Header wrapper -->
<nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling navbar">
    <div class="container">
        <a href="#" class="navbar-brand waves-effect">
            <strong class="blue-text">Chantee</strong>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a href="#" class="nav-link waves-effect">Menu</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link waves-effect">About us</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link waves-effect">Contacts</a>
                </li>
            </ul>
            <ul class="navbar-nav nav-flex-icons">
                <li class="nav-item">
                    <a href="#" class="nav-link waves-effect">
                        <span class="bage red z-depth-1 mr-1">0</span>
                        <i class="fa fa-shopping-cart"></i>
                        <span class="clearfix d-none d-sm-inline-block">Cart</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link waves-effect">
                        <i class="fas fa-sign-in-alt"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link waves-effect">
                        <i class="fas fa-language"></i>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<!-- Header wrapper -->


<!-- Main block wrapper-->
<main class="mt-5 pt-4">
    <div class="container dark-grey-text mt-5">
        <%
            for (int i = 0; i < 10; i++){
                response.getWriter().println(
                        "        <div class=\"row wow fadeIn\">\n" +
                        "            <div class=\"col-md-6 md-4\">\n" +
                        "                <img src=\"https://t8x8a5p2.stackpathcdn.com/wp-content/uploads/2018/05/Strawberry-Cheesecake-Recipe-Image-720x720.jpg\"\n" +
                        "                     alt=\"cake1\" class=\"img-fluid\" style=\"height: 300px;\">\n" +
                        "            </div>\n" +
                        "            <div class=\"col-md-6 md-4\">\n" +
                        "                <div class=\"p-4\">\n" +
                        "                    <div class=\"mb-3\">\n" +
                        "                        <span class=\"badge purple mr-1\">Some info</span>\n" +
                        "                    </div>\n" +
                        "                    <p class=\"lead\">\n" +
                        "                        <span class=\"mr-1\">PRICE</span>\n" +
                        "                    </p>\n" +
                        "                    <p class=\"lead font-weight-bold\">Decription</p>\n" +
                        "                    <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Iure omnis, natus quia quod temporibus\n" +
                        "                        sapiente error facilis aut voluptas. Optio architecto magni esse corrupti dolore sed non! Nulla,\n" +
                        "                        nam vero!</p>\n" +
                        "                    <form action=\"\" class=\"d-flex justify-content-left\">\n" +
                        "                        <input type=\"number\" class=\"form-control\" value=\"0\" aria-label=\"Search\" style=\"width:100px;\">\n" +
                        "                        <button type=\"submit\" class=\"btn btn-primary btn-md my-0 p\">\n" +
                        "                            Add to cart <i class=\"fa fa-shopping-cart ml-1\"></i>\n" +
                        "                        </button>\n" +
                        "                    </form>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "        <hr>\n");
            }%>

    </div>


    <!--PAGINATION -->
    <nav class="d-flex justify-content-center wow fadeIn">
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


<!--Footer block wrapper-->
<footer class="page-footer text-center font-small mt-4 wow fadeIn">
    <div class="pt-4">
        <a href="#" role="button" class="btn btn-outline-white">TEST <i class="fa fa-graduation-cap ml-2"></i></a>
        <a href="#" role="button" class="btn btn-outline-white">TEST1 <i class="fa fa-graduation-cap ml-2"></i></a>
    </div>
    <hr class="my-4">
    <div class="pb-4">
        <a href="#"><i class="mr-3 fab fa-facebook-f" aria-hidden="true"></i></a>
        <a href="#"><i class="mr-3 fab fa-instagram" aria-hidden="true"></i></a>
        <a href="#"><i class="mr-3 fab fa-youtube" aria-hidden="true"></i></a>
        <a href="#"><i class="mr-3 fab fa-pinterest" aria-hidden="true"></i></a>
    </div>
    <div class="footer-copyright py-3">
        Chantee delivery service
    </div>
</footer>
<!--Footer block wrapper-->


<!-- jQuery -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="js/mdb.min.js"></script>
</body>
</html>