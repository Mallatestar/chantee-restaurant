package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ServiceDAO;
import com.restaurant.chantee.model.domain.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

/**
 * Command to switch order status from kitchen to delivery
 */
public class ToDeliveryCommand implements Command{
    private static ServiceDAO dao = ServiceDAO.getInstance();
    void setDao(ServiceDAO newDAO){
        dao = newDAO;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        //Getting order id
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        //Getting kitchen orders list
        List<Order> kitchen = (List<Order>) session.getAttribute("kitchen");

        //Updating kitchen orders list
        session.removeAttribute("kitchen");
        kitchen.removeIf(o -> o.getId() == orderId);
        session.setAttribute("kitchen", kitchen);

        //Updating order status into DB
        try {
            dao.changeOrderStage(orderId, "delivery");
        } catch (DAOException e) {
            LOG.error("Can`t change order state", e);
            return "/error";
        }

        return "/manager-panel";
    }
}
