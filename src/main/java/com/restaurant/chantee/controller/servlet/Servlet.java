package com.restaurant.chantee.controller.servlet;


import com.restaurant.chantee.controller.command.Command;
import com.restaurant.chantee.controller.command.CommandPool;
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
import java.util.Arrays;
import java.util.Map;


@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
static final Logger LOG = LogManager.getLogger(Servlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("Called doGet method in Servlet");
        processing(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("Called doPost method in Servlet");
        processing(request, response);
    }

    private void processing(HttpServletRequest request,
                            HttpServletResponse response)
                            throws ServletException, IOException{
        LOG.debug("Request params before command:");
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String s : requestParams.keySet()){
            LOG.debug("Request param: " + s + "=" + Arrays.toString(requestParams.get(s)));
        }
        ServletContext servletContext = getServletContext();
        String commandName = request.getParameter("command");
        Command command = CommandPool.getCommand(commandName);
        String forward = command.execute(request, response);
        LOG.debug("Request params after command:");
        Map<String, String[]> requestParamsNew = request.getParameterMap();
        for (String s : requestParamsNew.keySet()){
            LOG.debug("Request param: " + s + "=" + Arrays.toString(requestParamsNew.get(s)));
        }
        if ( forward != null){
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(forward);
            requestDispatcher.forward(request, response);
        }
    }
}
