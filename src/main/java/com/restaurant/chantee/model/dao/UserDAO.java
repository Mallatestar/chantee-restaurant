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

/**
 * DAO to processing all actions with users
 */
public class UserDAO {
    private static UserDAO instance;
    private ConnectionPool cp = ConnectionPool.getInstance();

    public void setCp(ConnectionPool cp) {
        this.cp = cp;
    }

    static {
        instance = new UserDAO();
    }

    public static UserDAO getInstance(){
        if (instance == null){
            instance = new UserDAO();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return cp.getConnection();
    }

    /**
     * Creating a user into DB
     * @param username
     * @param email
     * @param user_password
     * @return User obj
     * @throws DAOException if will be some problems
     */
    public User createUser(String username, String email, String user_password) throws DAOException {
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
            connection = instance.getConnection();

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

    /**
     * Get a user from DB with such data
     * @param email Email from UI
     * @param user_password password hex from UI
     * @return User obj
     * @throws LoginException if there is no user with such email, or password is wrong
     * @throws DAOException if will be some problems
     */
    public User authenticateUser (String email, String user_password) throws LoginException, DAOException {
        User user = null;
        try{
            user = instance.findUserByEmail(email);
        } catch (NoSuchEntityException e) {
            LOG.error("Wrong authenticate data", e);
            throw new LoginException();
        }
        if (!Objects.equals(user.getUser_password(), user_password)){
            throw new LoginException();
        }
        return user;
    }

    /**
     * Finding a user in db ny email
     * @param email email from UI
     * @return user obj
     * @throws DAOException if will be some problems
     * @throws NoSuchEntityException if there is no user with such email
     */
    public User findUserByEmail(String email) throws DAOException, NoSuchEntityException {
        User user = new User();
        user.setEmail(email);
        Connection connection = null;
        PreparedStatement prep = null;
        ResultSet res = null;
        try {
            connection = instance.getConnection();
            prep = connection.prepareStatement(SQL.FIND_USER.getQuery());
            prep.setString(1, email);
            res = prep.executeQuery();
            if (res.next()){
                user.setId(res.getInt("id"));
                user.setUsername(res.getString("username"));
                user.setUser_password((res.getString("user_password")));
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

    /**
     * Find all users which are managers
     * @return List of managers user ids
     * @throws DAOException if will be some problems
     */
    public List<Integer> getAllManagers() throws DAOException {
        List<Integer> managers = new LinkedList<>();
        Connection connection = null;
        PreparedStatement prep = null;
        ResultSet res = null;
        try {
            connection = instance.getConnection();
            prep = connection.prepareStatement("SELECT * FROM managers;");
            res = prep.executeQuery();
            while (res.next()){
                managers.add(res.getInt("user_id"));
            }
        } catch (SQLException e) {
            LOG.error("Can`t get connection in getAllManagers()");
            throw new DAOException();
        }finally {
            closeQuietly(res);
            closeQuietly(prep);
            closeQuietly(connection);
        }

        return managers;
    }

    /**
     * Update username in DB
     * @param userName new name
     * @param id users id
     * @throws DAOException if will be some problems
     */
    public void renameUser(String userName, int id) throws DAOException {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = instance.getConnection();
            prep = connection.prepareStatement(SQL.RENAME_USER.getQuery());
            prep.setString(1, userName);
            prep.setInt(2, id);
            prep.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can`t get connection in renameUser()");
            throw new DAOException();
        }finally {
            closeQuietly(connection);
            closeQuietly(prep);
        }
    }

    /**
     * Updating users pass in the DB
     * @param newPass new password
     * @param id user id
     * @throws DAOException if will be some problems
     */
    public void changePass(String newPass, int id) throws DAOException {
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = instance.getConnection();
            prep = connection.prepareStatement(SQL.CHANGE_PASSWORD.getQuery());
            prep.setString(1, newPass);
            prep.setInt(2, id);
            prep.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can`t get connection in changePass()");
            throw new DAOException();
        }finally {
            closeQuietly(connection);
            closeQuietly(prep);
        }
    }

    /**
     * Deleting user from DB
     * @param id user id
     * @throws DAOException if will be some problems
     */
    public void deleteUser(int id) throws DAOException{
        Connection connection = null;
        PreparedStatement prep = null;
        try {
            connection = instance.getConnection();
            prep = connection.prepareStatement(SQL.DELETE_USER.getQuery());
            prep.setInt(1, id);
            prep.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can`t get connection in deleteUser()");
            throw new DAOException();
        }finally {
            closeQuietly(connection);
            closeQuietly(prep);
        }
    }
}
