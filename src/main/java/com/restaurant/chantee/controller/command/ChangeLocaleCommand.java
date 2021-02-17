package com.restaurant.chantee.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

public class ChangeLocaleCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute("locale");
        LOG.debug(locale);
        if (locale.equals("ru")){
            session.setAttribute("locale", "en");
        }
        if (locale.equals("en")){
            session.setAttribute("locale", "ru");
        }
        LOG.debug(session.getAttribute("locale"));
        return "/home";
    }
}
