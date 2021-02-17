package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.UserDAO;
import com.restaurant.chantee.model.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;
import static com.restaurant.chantee.controller.command.RegisterCommand.hash;

public class ChangeUserPassCommand implements Command{
    private static UserDAO dao = UserDAO.getInstance();
    void setDao(UserDAO newDAO){dao = newDAO;}

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        int id = user.getId();

        String pass = hash(request.getParameter("oldPassword"));
        if (Objects.equals(user.getUser_password(), pass)) {
            String newPass = hash(request.getParameter("newPassword"));
            try {
                dao.changePass(newPass, id);
            } catch (DAOException e) {
                LOG.error("Can`t change pass");
                return "/error";
            }
        }else {
            request.getSession().setAttribute("loginFailed", true);
        }
        return "/account";
    }
}
