package com.restaurant.chantee.controller.filter;

import com.restaurant.chantee.model.domain.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CartFilter", urlPatterns = "/cart.jsp")
public class CartFilter extends BaseFilter{
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ServletContext servletContext = session.getServletContext();
        if (user == null){
            String forward = "/register.jsp";
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(forward);
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                LOG.fatal(e);
            }
        }
    }
}
