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

/**
 * Command which removed a product from shopping cart
 */
public class RemoveProductCommand implements Command{
    private static ProductDAO dao = ProductDAO.getInstance();
    void setDao(ProductDAO newDAO){
        dao = newDAO;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //Getting shopping cart from session
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        //Getting product to remove from UI
        String productTitle = request.getParameter("productTitle");
        //Finding this product
        Product product = null;
        try {
            product = dao.findProductByTitle(productTitle);
        } catch (NoSuchEntityException | DAOException e) {
            LOG.error("Some problems in RemoveProductCommand DAO operation", e);
        }

        //Updating cart and session
        cart.removeProduct(product);
        session.removeAttribute("cart");
        session.setAttribute("cart", cart);
        return "/shopping-cart";
    }
}
