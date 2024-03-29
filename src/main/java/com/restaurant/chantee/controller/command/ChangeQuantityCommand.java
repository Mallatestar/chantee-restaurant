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
 * Command which changes quantity attribute in cart obj from session
 */
public class ChangeQuantityCommand implements Command{
    private static ProductDAO dao = ProductDAO.getInstance();
    void setDao(ProductDAO newDAO){
        dao = newDAO;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("CAlled ChangeQuantityCommand");
        HttpSession session = request.getSession();
        //Cart attribute from session
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        LOG.debug("Cart obj:" + cart);
        //Product from UI
        int productId = Integer.parseInt(request.getParameter("productId"));
        LOG.debug("Getted product id to change quantity:" + productId);
        //New quantity from UI
        Integer productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        LOG.debug("Getted product quantity:" + productQuantity);
        //Finding product in db
        Product product = null;
        try {
            product = dao.findProductById(productId);
        } catch (NoSuchEntityException | DAOException e) {
            LOG.error("Some problems in ChangeQuantityCommand DAO operation", e);
        }

        //Updating cart obj
        cart.changeQuantity(product, productQuantity);

        //Updating session
        session.removeAttribute("cart");
        session.setAttribute("cart", cart);
        return "/shopping-cart";
    }
}
