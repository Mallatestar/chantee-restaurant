<%@tag language="java" pageEncoding="UTF-8" %>
<%@attribute name="orderId" required="true" rtexprvalue="true" type="java.lang.Integer" %>
<form action="${pageContext.request.contextPath}/Servlet" method="post">
    <input type="hidden" name="command" value="orderFinishedCommand">
    <input type="hidden" name="orderId" value="${orderId}">
    <button class="btn btn-primary btn-md my-0 p" type="submit">
        Finish
    </button>
</form>