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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

/**
 * Command which create all order`s data and save the order into db
 */
public class TakeOrderCommand implements Command{
    private static ServiceDAO dao = ServiceDAO.getInstance();
    void setDao(ServiceDAO newDAO){
        dao = newDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        //Getting a cart obj from session
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        LOG.debug("Cart param from session: " + cart);

        //Getting a user obj from session
        User user = (User) session.getAttribute("user");
        LOG.debug("user from session params: " + user);


        //Creating a delivery data obj with params from UI
        DeliveryData deliveryData = new DeliveryData();
        deliveryData.setUser_id(user.getId());
        String phone = request.getParameter("phone");
        if (!validatePhone(phone)){ return "/error";}//Validating the phone number
        deliveryData.setPhone(phone);
        deliveryData.setAddress(request.getParameter("address"));
        LOG.debug("Generated delivery data: " + deliveryData);

        Order order = null;
        //Creating an order row into DB
        try {
            order = dao.registerOrder(user.getId(), java.time.LocalDateTime.now().toString(), request.getParameter("comment"), deliveryData);
        } catch (DAOException e) {
            LOG.error("Can`t create order", e);
            return "/error";
        }
        //Adding a products into order obj
        order.setCart(cart);

        //Creating a receipt of this order into db
        LOG.debug("order after DAO: " + order);
        try {
            dao.registerReceipt(cart.getCart(), order.getId());
        } catch (DAOException e) {
            LOG.error("Can`t register receipt");
            return "/error";
        }
        session.setAttribute("order", order);
        return "/receipt";
    }

    /**
     * Validating a phone number
     * @param phone phone string from UI
     * @return isVAlid
     */
    private boolean validatePhone(String phone){
        Matcher matcher = Pattern.compile("\\+\\d{12}").matcher(phone);
        return matcher.matches();
    }
}
