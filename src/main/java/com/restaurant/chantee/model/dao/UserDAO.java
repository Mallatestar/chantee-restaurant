package com.restaurant.chantee.model.dao;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.Exception.LoginException;
import com.restaurant.chantee.model.Exception.NoSuchEntityException;
import com.restaurant.chantee.model.domain.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static com.restaurant.chantee.model.dao.ConnectionPool.LOG;
import static com.restaurant.chantee.model.dao.ProductDAO.closeQuietly;

public class UserDAO {

    public static User createUser(String username, String email, String user_password) throws DAOException {
        LOG.debug("CreateUser( " + username + ", " + email + ", " + user_password + " );");
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setUser_password(user_password);

        Connection connection = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        ResultSet res = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();

            prep1 = connection.prepareStatement(SQL.ADD_USER.getQuery());
            prep1.setString(1, username);
            prep1.setString(2, email);
            prep1.setString(3, user_password);
            prep1.executeUpdate();

            prep2 = connection.prepareStatement(SQL.FIND_USER.getQuery());
            prep2.setString(1, email);
            res = prep2.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                LOG.debug("ID of finding user:" + user + " is :" + id);
                user.setId(id);
            }
        } catch (SQLException e) {
            LOG.error("Some problem with connection pool", e);
            throw new DAOException();
        }finally {
            closeQuietly(res);
            closeQuietly(prep1);
            closeQuietly(prep2);
            closeQuietly(connection);
        }


        return user;
    }

    public static User authenticateUser (String email, String user_password) throws LoginException, DAOException {
        User user = null;
        try{
            user = findUserByEmail(email);
        } catch (NoSuchEntityException e) {
            LOG.error("Wrong authenticate data", e);
            throw new LoginException();
        }
        if (!Objects.equals(user.getUser_password(), user_password)){
            throw new LoginException();
        }
        return user;
    }

    public static User findUserByEmail(String email) throws DAOException, NoSuchEntityException {
        User user = new User();
        user.setEmail(email);
        Connection connection = null;
        PreparedStatement prep = null;
        ResultSet res = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            prep = connection.prepareStatement(SQL.FIND_USER.getQuery());
            prep.setString(1, email);
            res = prep.executeQuery();
            if (res.next()){
                user.setId(res.getInt("id"));
                user.setUsername(res.getString("username"));
                user.setUser_password(res.getString("user_password"));
            }else {
                throw new NoSuchEntityException();
            }
        } catch (SQLException e) {
            LOG.error("Some problem with connection pool", e);
            throw new DAOException();
        } finally {
            closeQuietly(connection);
            closeQuietly(prep);
            closeQuietly(res);
        }
        return user;
    }

    public static List<Integer> getAllManagers() throws DAOException {
        List<Integer> managers = new LinkedList<>();
        Connection connection = null;
        PreparedStatement prep = null;
        ResultSet res = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            prep = connection.prepareStatement("SELECT * FROM managers;");
            res = prep.executeQuery();
            while (res.next()){
                managers.add(res.getInt("user_id"));
            }
        } catch (SQLException e) {
            LOG.error("Can`t get connection in getAllManagers()");
        }finally {
            closeQuietly(res);
            closeQuietly(prep);
            closeQuietly(connection);
        }

        return managers;
    }
}
