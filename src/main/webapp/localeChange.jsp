<%@ page import="static com.restaurant.chantee.controller.listener.ConnectionListener.LOG" %>
<%@ include file="/WEB-INF/include/head.jspf"%>

<%LOG.debug("Change Locale");
    String locale = (String) session.getAttribute("locale");
    LOG.debug(locale);
    if (locale.equals("ru")){
        session.setAttribute("locale", "en");
    }
    if (locale.equals("en")){
        session.setAttribute("locale", "ru");
    }
    LOG.debug(session.getAttribute("locale"));
%>

<jsp:forward page="index.jsp"/>