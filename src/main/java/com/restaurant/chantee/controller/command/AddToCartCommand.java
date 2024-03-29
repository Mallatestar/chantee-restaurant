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
 * Command which adding a product obj from db to cart obj in session
 */
public class AddToCartCommand implements Command{
    private static ProductDAO dao = ProductDAO.getInstance();
    void setDao(ProductDAO newDAO){
        dao = newDAO;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //Creating cart if not exist
        if (session.getAttribute("user") == null){return "/register.jsp";}
        if (session.getAttribute("cart") == null){
            session.setAttribute("cart", new ShoppingCart());
        }

        //Product title from UI
        int productId = Integer.parseInt(request.getParameter("productId"));
        LOG.debug("Getted product title:" + productId);
        //Product quantity from UI
        Integer productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
        LOG.debug("Getted product quantity: " + productQuantity);

        //Creating product from db
        Product product = null;
        try {
            product = dao.findProductById(productId);
        } catch (NoSuchEntityException e) {
            LOG.error("Can`t find product: " + productId, e);
            return "/error.jsp";
        } catch (DAOException e) {
            LOG.error("Some problems in DAO", e);
            return "/error.jsp";
        }
        LOG.debug("Result product:" + product);

        //Getting cart obj from session and updating it
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        LOG.debug("Getted cart obj: " + cart);
        session.removeAttribute("cart");
        cart.addProduct(product, productQuantity);
        LOG.debug("resulted cart: " + cart);
        session.setAttribute("cart", cart);
        LOG.debug("Resolted session attribute \"cart\": " + cart);

        return "/menu";
    }
}
