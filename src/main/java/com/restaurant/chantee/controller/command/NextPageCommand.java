package com.restaurant.chantee.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command which change page number attr in session, to generate the next product list from pagination map
 */
public class NextPageCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int pageNum = (int) session.getAttribute("categoryPageNumber");
        session.removeAttribute("categoryPageNumber");
        session.setAttribute("categoryPageNumber", (pageNum + 1));
        return "/menu";
    }
}
