package com.restaurant.chantee.model.dao;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of object pool pattern, used to manage database connections;
 * Instance of this class will be created by DbConnectionPoolListener when web app has been deployed
 */

public final class ConnectionPool {

    private ConnectionPool() {
        //private constructor for singleton
    }

    private static ConnectionPool instance = null;

    public static synchronized ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() throws NamingException, SQLException {
        Connection conn = null;
        Context context = new InitialContext();
        DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/chanteedb");
        conn = ds.getConnection();
        return conn;
    }

}
