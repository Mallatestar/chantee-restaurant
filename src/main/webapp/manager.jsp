<%@ page contentType="text/html;charset=UTF-8"%>
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

    <form action="${pageContext.request.contextPath}/Servlet">
        <input type="hidden" name="command" value="prepareManagerData">
        <button type="submit">Refresh data</button>
    </form>
    <section class="text-center mb-4">
        <!--Single row-->
        <div class="row mt-2">
            <!--Single element-->
            <div class="col-lg-3 col-md-6 mb-4" >
                <h3>Ordered</h3>
                <div class="container">
                    Order ID: 123<br>
                    Phone number: +3800000000<br>
                    Delivery address:<br>
                    Some address<br>
                    Product list:<br>
                    <table>
                        <tr>
                            <td>Title</td>
                            <td>Quantity</td>
                        </tr>
                        <tr>
                            <td>Name</td>
                            <td>10</td>
                        </tr>
                    </table><br>
                    Total price: 100500<br>
                    <form action="#">
                        <input type="hidden" name="command" value="acceptOrderCommand">
                        <input type="hidden" name="orderId" value="123">
                        <button type="submit">Accept order</button>
                    </form>
                    <form action="#">
                        <input type="hidden" name="command" value="discardOrderCommand">
                        <input type="hidden" name="orderId" value="123">
                        <button type="submit">Discard order</button>
                    </form>
                </div>
            </div>
            <!--Single element-->
            <div class="col-lg-3 col-md-6 mb-4" >
                <h3>Kitchen</h3>
                <table border="1">
                    <tr>
                        <td>Order ID</td>
                        <td>Getted time</td>
                    </tr>
                    <tr>
                        <td>Test1</td>
                        <td>Test2</td>
                    </tr>
                </table>
            </div>
            <!--Single element-->
            <div class="col-lg-3 col-md-6 mb-4" >
                <h3>Delivery</h3>
                <table border="1">
                    <tr>
                        <td>Order ID</td>
                        <td>Getted time</td>
                    </tr>
                    <tr>
                        <td>Test1</td>
                        <td>Test2</td>
                    </tr>
                </table>
            </div>
            <!--Single element-->

        </div>
        <!--Single row-->
    </section>
</main>

</body>
</html>
