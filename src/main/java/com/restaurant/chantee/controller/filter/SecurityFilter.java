package com.restaurant.chantee.controller.filter;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.UserDAO;
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
import java.util.List;


@WebFilter(filterName = "SecurityFilter", urlPatterns = "/manager.jsp")
public class SecurityFilter extends BaseFilter{
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Integer> managers = null;
        ServletContext servletContext = session.getServletContext();
        try {
            managers = UserDAO.getAllManagers();
        } catch (DAOException e) {
            LOG.error("Can`t find managers", e);
        }

        if (user == null || managers!= null && !managers.contains(user.getId())){
            String forward = "/index.jsp";
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(forward);
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                LOG.fatal(e);
            }
        }
    }
}
