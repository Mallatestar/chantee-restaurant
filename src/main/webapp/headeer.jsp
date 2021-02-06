
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <a href="${pageContext.request.contextPath}/login.jsp" class="nav-link waves-effect">
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