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

public class ChangeQuantityCommand implements Command{
    private static ProductDAO dao = ProductDAO.getInstance();
    void setDao(ProductDAO newDAO){
        dao = newDAO;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        String productTitle = request.getParameter("productTitle");
        Integer productQuantity = Integer.parseInt(request.getParameter("productQuantity"));

        Product product = null;
        try {
            product = dao.findProductByTitle(productTitle);
        } catch (NoSuchEntityException | DAOException e) {
            LOG.error("Some problems in ChangeQuantityCommand DAO operation", e);
        }

        cart.changeQuantity(product, productQuantity);

        session.removeAttribute("cart");
        session.setAttribute("cart", cart);
        return "/cart.jsp";
    }
}
