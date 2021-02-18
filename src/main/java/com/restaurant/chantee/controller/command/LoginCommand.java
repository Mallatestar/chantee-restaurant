package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.Exception.LoginException;
import com.restaurant.chantee.model.dao.UserDAO;
import com.restaurant.chantee.model.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;
import static com.restaurant.chantee.controller.command.RegisterCommand.hash;

/**
 * Command which authenticates user and set user obj into session
 */
public class LoginCommand implements Command{

    private static UserDAO dao = UserDAO.getInstance();
    void setDao(UserDAO newDAO){
        dao = newDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //Getting and validating user`s email from UI
        String email = (request.getParameter("email"));
        LOG.debug("Validation: " + validate(email));
        if (!validate(email)){return "/error";}

        //Getting user`s  password hex
        String user_password = hash((request.getParameter("user_password")));
        LOG.debug("Login params: " + email + ", " + user_password);

        //Preparing user obj
        User user = null;

        //Authenticating user
        try {
            user = dao.authenticateUser(email, user_password);
        } catch (DAOException e) {
            LOG.error("Some problems with authenticate user: " + email, e);
            return "/error";
        //If user`s data is wrong -- alerting user
        } catch (LoginException e) {
            LOG.error("Wrong email or password", e);
            request.getSession().setAttribute("loginFailed", true);
            return "/sign-in";
        }

        //Updating session
        request.getSession().setAttribute("user", user);
        return "/home";
    }

    /**
     * Validation of email string
     * @param email email from UI
     * @return isValid
     */
    static boolean validate(String email){
        Pattern emailPattern = Pattern.compile(".*@.*\\..*");
        Matcher emailMather = emailPattern.matcher(email);
        return emailMather.matches();
    }

}
