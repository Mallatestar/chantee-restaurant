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

public class AddToCartCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null){return "/register.jsp";}
        if (session.getAttribute("cart") == null){
            session.setAttribute("cart", new ShoppingCart());
        }
        String productTitle = request.getParameter("productTitle");
        LOG.debug("Getted product title:" + productTitle);
        Integer productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        LOG.debug("Getted product quantity: " + productQuantity);
        Product product = null;
        try {
            product = ProductDAO.findProductByTitle(productTitle);
        } catch (NoSuchEntityException e) {
            LOG.error("Can`t find product: " + productTitle, e);
            return "/error.jsp";
        } catch (DAOException e) {
            LOG.error("Some problems in DAO", e);
            return "/error.jsp";
        }
        LOG.debug("Result product:" + product);
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        LOG.debug("Geted card obj: " + cart);
        session.removeAttribute("cart");
        cart.addProduct(product, productQuantity);
        LOG.debug("resulted cart: " + cart);
        session.setAttribute("cart", cart);
        LOG.debug("Resolted session attribute \"cart\": " + cart);

        return "/category-page.jsp";
    }
}
