package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ServiceDAO;
import com.restaurant.chantee.model.domain.entity.DeliveryData;
import com.restaurant.chantee.model.domain.entity.Order;
import com.restaurant.chantee.model.domain.entity.ShoppingCart;
import com.restaurant.chantee.model.domain.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

public class TakeOrderCommand implements Command{
    private static ServiceDAO dao = ServiceDAO.getInstance();
    void setDao(ServiceDAO newDAO){
        dao = newDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        LOG.debug("Cart param from session: " + cart);

        User user = (User) session.getAttribute("user");
        LOG.debug("user from session params: " + user);

        DeliveryData deliveryData = new DeliveryData();
        deliveryData.setUser_id(user.getId());
        deliveryData.setPhone(request.getParameter("phone"));
        deliveryData.setAddress(request.getParameter("address"));
        LOG.debug("Generated delivery data: " + deliveryData);

        Order order = null;
        try {
            order = dao.registerOrder(user.getId(), java.time.LocalDateTime.now().toString(), request.getParameter("comment"), deliveryData);
        } catch (DAOException e) {
            LOG.error("Can`t create order", e);
            return "/error.jsp";
        }

        order.setCart(cart);

        LOG.debug("order after DAO: " +order);
        try {
            dao.registerReceipt(cart.getCart(), order.getId());
        } catch (DAOException e) {
            LOG.error("Can`t register receipt");
            return "error.jsp";
        }
        session.setAttribute("order", order);
        return "/receipt.jsp";
    }
}
