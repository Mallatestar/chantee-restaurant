package com.restaurant.chantee.chantee_restaurant.controller.servlet;

import com.restaurant.chantee.chantee_restaurant.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
    static final Logger LOGGER = LogManager.getLogger(Servlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text");
        User user = new User();
        user.setUsername("Vasya");
        LOGGER.debug(user + "was created");
        PrintWriter writer = response.getWriter();
        writer.println(user);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
