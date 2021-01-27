package com.restaurant.chantee.controller.servlet;

import com.restaurant.chantee.model.ModelException;
import com.restaurant.chantee.model.domain.UserOperations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.restaurant.chantee.controller.servlet.RegisterServlet.hashPass;

@WebServlet(name = "Authentication", value = "/Authentication")
public class AuthenticationServlet extends HttpServlet {
    static final Logger LOGGER = LogManager.getLogger(AuthenticationServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        if (request.getSession().getAttribute("user") != null){
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request, response);
        }
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/pages/authentication.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//TODO stupid method? rework it!
        ServletContext servletContext = getServletContext();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            if (UserOperations.authenticateUser(email, hashPass(password))){
                request.getSession().setAttribute("user", UserOperations.findUserByEmail(email));
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ModelException e) {
            LOGGER.error("Can`t authenticateUser, due to:", e);
        }
    }
}
