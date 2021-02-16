<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/include/head.jspf" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">
    <html>
    <head>
        <title><fmt:message key="receipt_title"/> </title>
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
        <link rel="stylesheet" href="view/css/receipt.css">
    </head>
    <body>
    <table class="body-wrap">
        <tbody>
        <tr>
            <td></td>
            <td class="container" width="600">
                <div class="content">
                    <table class="main" width="100%" cellpadding="0" cellspacing="0">
                        <tbody>
                        <tr>
                            <td class="content-wrap aligncenter">
                                <table width="100%" cellpadding="0" cellspacing="0">
                                    <tbody>
                                    <tr>
                                        <td class="content-block">
                                            <h2>
                                                <fmt:message key="receipt_thx"/>
                                            </h2>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="content-block">
                                            <table class="invoice">
                                                <tbody>
                                                <tr>
                                                    <td>
                                                        <fmt:message key="receipt_num"/> # ${sessionScope.order.id}<br>
                                                        <fmt:message key="receipt_date"/> :${sessionScope.order.dateTime}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table class="invoice-items" cellpadding="0" cellspacing="0">
                                                            <tbody>
                                                            <c:forEach var="prod" items="${sessionScope.order.cart.cart}">
                                                            <tr>
                                                                <td>${prod.key.title}</td>
                                                                <td>${prod.value}</td>
                                                                <td class="alignright">${prod.key.price} UAH</td>
                                                            </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <fmt:message key="receipt_total"/> : ${sessionScope.order.cart.totalPrice} UAH<br>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="content-block">
                                            <form action="${pageContext.request.contextPath}/Servlet" method="post">
                                                <input type="hidden" name="command" value="endPurchaseCommand">
                                                <button type="submit">
                                                    <fmt:message key="receipt_return"/>
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="content-block">
                                            <a href="#"><fmt:message key="receipt_download"/> </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="content-block">
                                            <fmt:message key="footer_name"/>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="footer">
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td class="aligncenter content-block"><fmt:message key="receipt_mail"/> :<a href="mailto:">support@company.inc</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </td>
            <td></td>
        </tr>
        </tbody>
    </table>


    <jsp:include page="footer.jsp"/>
    </body>
    </html>
</fmt:bundle>