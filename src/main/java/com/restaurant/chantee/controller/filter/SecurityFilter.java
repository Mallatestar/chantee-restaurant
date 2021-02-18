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

/**
 * Defender of manager panel from not-manager users
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/manager.jsp", "/manager-panel"})
public class SecurityFilter extends BaseFilter{
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        HttpSession session = request.getSession();
        //Getted user obj from session
        User user = (User) session.getAttribute("user");
        List<Integer> managers = null;
        ServletContext servletContext = session.getServletContext();

        //Getted managers list
        try {
            managers = UserDAO.getInstance().getAllManagers();
        } catch (DAOException e) {
            LOG.error("Can`t find managers", e);
        }

        //All not-manager sessions will be redirected to home page
        if (user == null || managers!= null && !managers.contains(user.getId())){
            String forward = "/home";
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(forward);
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                LOG.fatal(e);
            }
        // Manager user can access a manager panel
        }else {
            String forward = "/manager-panel";
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(forward);
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                LOG.fatal(e);
            }
        }
    }
}
