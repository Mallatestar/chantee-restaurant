<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body>
<%@include file="headeer.jsp"%>

<main class="mt-5 pt-5" style="height: 85%">
    <div class="container dark-grey-text">
        <div class="row container display-flex justify-content-center">
               <h1 class="lable">Shopping cart</h1> 
        </div>
        <div style="border: 1px solid" class="row">
            <div class="col-md-6 ">
                <img style="height: 200px;" src="view/img/goods/cakes/2cce38dd5542c967a82b3e0f32c58360.jpg" alt="1">
            </div>
            <div class="col-md-6  container display-flex">
                <div class="p-4">
                    <div class="mb-3">
                        <span class="badge purple mr-1">Product</span>
                    </div>
                    <p class="lead">
                        <span class="mr-1">PRICE</span>
                    </p>
                    <form method="post" action="${pageContext.request.contextPath}/CartServlet" class="d-flex justify-content-left">
                        <input name="productName" type="hidden" value="tortik">
                        <input name="productQuantity" type="number" class="form-control" value="0" aria-label="Search" style="width:100px;" aria-valuemin="1">
                        <button type="submit" class="btn btn-primary btn-md my-0 p">
                            Change quantity
                        </button>
                        <button type="submit" class="btn btn-primary btn-md my-0 p">
                            Remove position
                        </button>
                    </form>
                </div>
            </div>
     </div>
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