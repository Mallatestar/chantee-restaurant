package com.restaurant.chantee.model.domain;

import com.restaurant.chantee.model.ModelException;
import com.restaurant.chantee.model.dao.UserDAO;
import com.restaurant.chantee.model.domain.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class UserOperations {
    static final Logger LOGGER = LogManager.getLogger(UserOperations.class);

    public static void registerNewUser(User user) throws ModelException {
        if (!UserDAO.findAllUsers().contains(user)){
            UserDAO.addUser(user);
        }
    }

    public static boolean authenticateUser(String email, String password) throws ModelException {
        User user = UserDAO.findUser(email);
        return Objects.equals(user.getPassword(), password);
    }

    public static User findUserByEmail(String email) throws ModelException {
        return UserDAO.findUser(email);
    }




}
