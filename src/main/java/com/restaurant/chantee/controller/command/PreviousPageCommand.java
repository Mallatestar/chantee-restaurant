package com.restaurant.chantee.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PreviousPageCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int pageNum = (int) session.getAttribute("categoryPageNumber");
        session.removeAttribute("categoryPageNumber");
        session.setAttribute("categoryPageNumber", (pageNum - 1));
        return "/category-page.jsp";
    }
}
