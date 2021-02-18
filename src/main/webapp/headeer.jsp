<%@ include file="/WEB-INF/include/head.jspf"%>
<c:if test="${sessionScope.locale == null}">
    <c:set scope="session" var="locale" value="ru"/>
</c:if>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">
<nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling navbar">
    <div class="container">
        <a href="${pageContext.request.contextPath}/home" class="navbar-brand waves-effect">
            <strong class="blue-text">Chantee</strong>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <form action="${pageContext.request.contextPath}/Servlet" method="post">
                        <input name="command" value="generateProductList" type="hidden">
                        <input type="hidden" name="category" value="all">
                        <button class="btn btn-primary btn-md my-0 p" type="submit">
                            <fmt:message key="header_Menu_button"/>
                        </button>
                    </form>

                </li>
            </ul>
            <ul class="navbar-nav nav-flex-icons">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/shopping-cart" class="nav-link waves-effect">
                        <i class="fa fa-shopping-cart"></i>
                        <span class="clearfix d-none d-sm-inline-block">
                            <fmt:message key="header_Cart_span"/>
                        </span>
                    </a>
                </li>
                <c:if test="${sessionScope.user == null}">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/sign-in" class="nav-link waves-effect">
                        <i class="fas fa-sign-in-alt"></i>
                    </a>
                </li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/account" class="nav-link waves-effect">
                            <i class="fas fa-address-card"></i>
                        </a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <form action="${pageContext.request.contextPath}/Servlet" method="post">
                        <input type="hidden" name="command" value="changeLocaleCommand">
                        <button type="submit" class="nav-link waves-effect"><i class="fas fa-language"></i></button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
</fmt:bundle>