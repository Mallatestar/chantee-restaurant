package com.restaurant.chantee.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command which finished purchase cycle and clean up users session
 */
public class EndPurchaseCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("cart");
        request.getSession().removeAttribute("order");
        request.getSession().removeAttribute("products");
        return "/home";
    }
}
