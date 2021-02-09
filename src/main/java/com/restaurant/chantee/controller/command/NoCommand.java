package com.restaurant.chantee.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/error.jsp";
    }
}