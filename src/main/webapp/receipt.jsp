<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container flex-center">
    <h3>Receipt</h3>
    <h4>Thank you for order!</h4>
    <div class="container">
        Product ............. Quantity ............. Price<br>
        <c:forEach var="prod" items="${sessionScope.order.cart.cart}">
            ${prod.key.title} ............. ${prod.value} .............${prod.key.price} <br>
        </c:forEach>
        Total price : ${sessionScope.order.cart.totalPrice}
    </div>

    <form action="${pageContext.request.contextPath}/Servlet" method="post">
        <input type="hidden" name="command" value="endPurchaseCommand">
        <button type="submit">
            Return
        </button>
    </form>

</div>

</body>
</html>
