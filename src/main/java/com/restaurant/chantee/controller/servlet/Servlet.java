package com.restaurant.chantee.controller.servlet;


import com.restaurant.chantee.controller.command.Command;
import com.restaurant.chantee.controller.command.CommandPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Implementation of Controller in MVC
 * Implementation of PRG pattern
 * Get request from UI, execute a command on model and send redirect on view element
 */

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
static final Logger LOG = LogManager.getLogger(Servlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.error("Called doGet method in Servlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("Called doPost method in Servlet");
        processing(request, response);
    }

    private void processing(HttpServletRequest request,
                            HttpServletResponse response)
                            throws ServletException, IOException{

        //Getting command parameter from UI
        String commandName = request.getParameter("command");
        //Find a command in Command pool
        Command command = CommandPool.getCommand(commandName);
        //Send execute to command
        String forward = command.execute(request, response);
        //Create response
        response.sendRedirect(request.getContextPath() + forward);
    }
}
