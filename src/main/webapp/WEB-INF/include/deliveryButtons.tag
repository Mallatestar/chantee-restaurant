<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="order_id" required="true" rtexprvalue="false" %>
<form action="${pageContext.request.contextPath}/Servlet" method="post">
          <input type="hidden" name="command" value="toDeliveryCommand">
          <input type="hidden" name="orderId" value="${order_id}">
          <button class="btn btn-primary btn-md my-0 p\" type="submit">
              To delivery
          </button>
</form>