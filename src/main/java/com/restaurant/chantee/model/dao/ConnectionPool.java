package com.restaurant.chantee.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of object pool pattern, used to manage database connections.
 * Created with tomcat JNDI.
 */

public final class ConnectionPool {
    static final Logger LOG = LogManager.getLogger(ConnectionPool.class);

    private ConnectionPool() {
        //private constructor for singleton
    }

    private static ConnectionPool instance = null;

    public static synchronized ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Context context;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/chanteedb");
            conn = ds.getConnection();
        } catch (NamingException e) {
            LOG.error("Some problems with connection pool", e);
        }
        return conn;
    }

}
