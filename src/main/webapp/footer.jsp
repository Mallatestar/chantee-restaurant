<%@ include file="/WEB-INF/include/head.jspf"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="locale">
<!--Footer block wrapper-->
<footer class="page-footer text-center font-small mt-4 wow fadeIn">
    <hr class="my-4">
    <div class="pb-4">
        <a href="#"><i class="mr-3 fab fa-facebook-f" aria-hidden="true"></i></a>
        <a href="#"><i class="mr-3 fab fa-instagram" aria-hidden="true"></i></a>
        <a href="#"><i class="mr-3 fab fa-youtube" aria-hidden="true"></i></a>
        <a href="#"><i class="mr-3 fab fa-pinterest" aria-hidden="true"></i></a>
    </div>
    <div class="footer-copyright py-3">
        <fmt:message key="footer_name"/>
    </div>
</footer>
<!--Footer block wrapper-->
    </fmt:bundle>