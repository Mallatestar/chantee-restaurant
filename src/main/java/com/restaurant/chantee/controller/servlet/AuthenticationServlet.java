package com.restaurant.chantee.controller.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        /*
        try {
            if (UserOperations.authenticateUser(email, hashPass(password))){
                request.getSession().setAttribute("user", UserOperations.findUserByEmail(email));
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ModelException e) {
            LOGGER.error("Can`t authenticateUser, due to:", e);
        }

         */
    }
}
