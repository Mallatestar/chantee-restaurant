package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.UserDAO;
import com.restaurant.chantee.model.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

public class RegisterCommand implements Command{
    private static UserDAO dao = UserDAO.getInstance();

    void setDao(UserDAO newDAO){
        dao = newDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = (request.getParameter("username"));
        String email = (request.getParameter("email"));
        LOG.debug("Validation: " + validate(username, email));
        if (!validate(username, email)){return "/error.jsp";}

        String user_password = hash((request.getParameter("user_password")));
        LOG.debug("Register params: " + username + ", " + email + ", " +user_password);
        User user = null;
        try {
            user =dao.createUser(username, email, user_password);
        } catch (DAOException e) {
            LOG.error("Cant create user", e);
            return "/error.jsp";
        }
        request.getSession().setAttribute("user", user);
        LOG.debug("Created a new user: " + user);
        return "/index.jsp";
    }


    static String hash(String input) {
        String hex = "";
        MessageDigest messageDigest;
        byte[] digest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(input.getBytes());
            digest = messageDigest.digest();
            hex = String.format("%064x", new BigInteger(1, digest)).toUpperCase();
        } catch (Exception e) {
            LOG.error("Some problems with hashing password" + input, e);
        }
        return hex;
    }

    static boolean validate( String username, String email){
        Pattern usernamePattern = Pattern.compile("[A-Za-zА-Яа-яёЁ]*");
        Matcher usernameMatcher = usernamePattern.matcher(username);
        if (!usernameMatcher.matches()){return false;}
        Pattern emailPattern = Pattern.compile(".*@.*\\..*");
        Matcher emailMather = emailPattern.matcher(email);
        return emailMather.matches();
    }
}
