package com.restaurant.chantee.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeReturnCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("from", (request.getParameter("jspName")));

        return "/test2.jsp";
    }
}
