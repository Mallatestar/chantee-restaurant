package com.restaurant.chantee.model.dao;

import com.restaurant.chantee.model.ModelException;
import com.restaurant.chantee.model.domain.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDAO {
    static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    public static void addUser(User user) throws ModelException {
        try(Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement prep = conn.prepareStatement(SQL.ADD_USER.getQuery()))
        {
            prep.setString(1, user.getUsername());
            prep.setString(2, user.getEmail());
            prep.setString(3, user.getPassword());
            prep.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Failed adding a \"" + user + "\", due to:", e);
            throw new ModelException(e.getMessage());
        }
    }

    public static List<User> findAllUsers() throws ModelException {
        List<User> result = new LinkedList<>();
        ResultSet res = null;
        Connection conn = null;
        PreparedStatement prep = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            prep = conn.prepareStatement(SQL.FIND_ALL_USERS.getQuery());
            res = prep.executeQuery();

            while (res.next()){
                User user = new User();
                user.setId(res.getInt("id"));
                user.setUsername(res.getString("username"));
                user.setEmail(res.getString("email"));
                user.setPassword(res.getString("user_password"));
                result.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t find all users due to:", e);
            throw new ModelException(e.getMessage());
        }finally {
            closeResources(res, prep, conn);
        }
        return result;
    }

    public static User findUser(String email) throws ModelException {
        User user = new User();
        ResultSet res = null;
        Connection conn = null;
        PreparedStatement prep = null;
        try{
            conn = ConnectionPool.getInstance().getConnection();
            prep = conn.prepareStatement(SQL.FIND_USER.getQuery());
            prep.setString(1, email);
            res = prep.executeQuery();

            while (res.next()){
                user.setId(res.getInt("id"));
                user.setUsername(res.getString("username"));
                user.setEmail(res.getString("email"));
                user.setPassword(res.getString("user_password"));
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t find user to:", e);
            throw new ModelException(e.getMessage());
        }finally {
            closeResources(res, prep, conn);
        }
        return user;
    }


    private static void closeResources(ResultSet res, PreparedStatement prep, Connection conn){
        try {
            if (res != null) {
                res.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t close result set");
        }
        try {
            if (prep != null) {
                prep.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t close prepared statment");
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t close connection");
        }
    }
    private static void closeResources(PreparedStatement prep, Connection conn){
        try {
            if (prep != null) {
                prep.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t close prepared statment");
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t close connection");
        }
    }
}
