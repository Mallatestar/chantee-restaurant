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
 * Filter which redirect user to right page, depends of it`s permissions
 */

@WebFilter(filterName = "AccountFilter", urlPatterns = {"/account.jsp", "/account"})
public class AccountFilter extends BaseFilter{
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        HttpSession session = request.getSession();
        //Getting a user obj from session
        User user = (User) session.getAttribute("user");

        List<Integer> managers = null;
        ServletContext servletContext = session.getServletContext();

        //Finding all meneger users from DB
        try {
            managers = UserDAO.getInstance().getAllManagers();
        } catch (DAOException e) {
            LOG.error("Can`t find managers", e);
        }


        //If user is not logged in -- redirect to login page
        if (user == null){
            String forward = "/sign-up";
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(forward);
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                LOG.fatal(e);
            }
        //If user is manager -- redirect to manager-panel
        }else if (managers != null && managers.contains(user.getId())){
            String forward = "/manager-panel";
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(forward);
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                LOG.fatal(e);
            }
        //If user is customer -- redirect to account page
        }else {
            String forward = "/account";
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(forward);
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                LOG.fatal(e);
            }
        }
    }
}
