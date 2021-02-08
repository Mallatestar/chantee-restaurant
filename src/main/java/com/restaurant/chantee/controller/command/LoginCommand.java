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

public class LoginCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String email = (request.getParameter("email"));
        LOG.debug("Validation: " + validate(email));
        if (!validate(email)){return "/error.jsp";}
        String user_password = hash((request.getParameter("user_password")));
        LOG.debug("Login params: " + email + ", " + user_password);
        User user = null;
        try {
            user = UserDAO.authenticateUser(email, user_password);
        } catch (DAOException e) {
            LOG.error("Some problems with authenticate user: " + email, e);
        } catch (LoginException e) {
            LOG.error("Wrong email or password", e);
            return "/error.jsp";
        }
        request.getSession().setAttribute("user", user);
        return "/index.jsp";
    }

    static boolean validate(String email){
        Pattern emailPattern = Pattern.compile(".*@.*\\..*");
        Matcher emailMather = emailPattern.matcher(email);
        return emailMather.matches();
    }

}
