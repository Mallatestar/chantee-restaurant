package com.restaurant.chantee.controller.servlet;

import com.restaurant.chantee.model.ModelException;
import com.restaurant.chantee.model.domain.UserOperations;
import com.restaurant.chantee.model.domain.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Register", value = "/Register")
public class RegisterServlet extends HttpServlet {
    static final Logger LOGGER = LogManager.getLogger(RegisterServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/view/pages/register.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(hashPass(request.getParameter("password")));
        String errorPage = "/view/pages/error.jsp";
        String mainPage = "/index.jsp";
        ServletContext servletContext = getServletContext();
        try {
            UserOperations.registerNewUser(user);
            request.getSession().setAttribute("user", user);
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(mainPage);
            requestDispatcher.forward(request, response);
        } catch (ModelException e) {
            LOGGER.error("User register is failed", e);
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(errorPage);
            requestDispatcher.forward(request, response);
        }
    }


    public static String hashPass(String passwordToHash) {
        String hex = "";
        MessageDigest messageDigest;
        byte[] digest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(passwordToHash.getBytes());
            digest = messageDigest.digest();
            hex = String.format("%064x", new BigInteger(1, digest)).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            //todo: add logger here
        }
        return hex;
    }
}
