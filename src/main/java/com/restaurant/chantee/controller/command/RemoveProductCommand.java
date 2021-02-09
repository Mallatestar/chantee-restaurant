package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.Exception.NoSuchEntityException;
import com.restaurant.chantee.model.dao.ProductDAO;
import com.restaurant.chantee.model.domain.entity.Product;
import com.restaurant.chantee.model.domain.entity.ShoppingCart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

public class RemoveProductCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        String productTitle = request.getParameter("productTitle");
        Product product = null;
        try {
            product = ProductDAO.findProductByTitle(productTitle);
        } catch (NoSuchEntityException | DAOException e) {
            LOG.error("Some problems in RemoveProductCommand DAO operation", e);
        }
        cart.removeProduct(product);
        session.removeAttribute("cart");
        session.setAttribute("cart", cart);
        return "/cart.jsp";
    }
}