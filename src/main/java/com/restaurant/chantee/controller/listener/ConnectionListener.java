package com.restaurant.chantee.controller.listener;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener to check DB connection when application starts
 */
@WebListener
public class ConnectionListener implements ServletContextListener{
    public static final Logger LOG = LogManager.getLogger(ConnectionListener.class);
    public ConnectionListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        try {
            Class.forName("com.restaurant.chantee.model.dao.ConnectionPool");
            LOG.info("Everything alright!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

}
