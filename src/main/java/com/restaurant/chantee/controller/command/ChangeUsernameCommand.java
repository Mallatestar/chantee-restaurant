package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.UserDAO;
import com.restaurant.chantee.model.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

/**
 * Command which updating username in db
 */
public class ChangeUsernameCommand implements Command{
    private static UserDAO dao = UserDAO.getInstance();
    void setDao(UserDAO newDAO){dao = newDAO;}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //New username from UI
        String userName = request.getParameter("username");
        //User obj from session to get user ID
        User user = (User) request.getSession().getAttribute("user");
        request.getSession().removeAttribute("user");
        int id = user.getId();

        //Updating table row
        try {
            dao.renameUser(userName, id);
        } catch (DAOException e) {
            LOG.error("Can`t rename user");
            return "/error";
        }

        //Updating session
        user.setUsername(userName);
        request.getSession().setAttribute("user", user);
        return "/account";
    }
}
