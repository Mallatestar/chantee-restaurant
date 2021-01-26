package com.restaurant.chantee.controller.listener;

import com.restaurant.chantee.model.dao.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;

/**
 * This listener-class is needed just for initializing DataSource implementation class in model.dao.ConnectionPool.
 */

@WebListener
public class DbConnectionPoolInitListener implements ServletContextListener {

    static final Logger LOGGER = LogManager.getLogger(DbConnectionPoolInitListener.class);
    private static ConnectionPool connectionPool;

    public DbConnectionPoolInitListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //This method is called when the servlet context is initialized(when the Web application is deployed).
        LOGGER.info("Web app STARTED");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //This method is called when the servlet Context is undeployed or Application Server shuts down.
        LOGGER.info("Web app STOPPED");
    }

}
