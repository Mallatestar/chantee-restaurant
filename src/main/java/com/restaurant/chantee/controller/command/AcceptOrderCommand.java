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
 * Command to switch order status from ordered to kitchen
 */
public class AcceptOrderCommand implements Command{
    private static ServiceDAO dao = ServiceDAO.getInstance();
    void setDao(ServiceDAO newDAO){
        dao = newDAO;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        //Order id from UI
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        //List of orders with status ordered from db
        List<Order> ordered = (List<Order>) session.getAttribute("ordered");

        session.removeAttribute("ordered");

        //deleting this order from ordered list
        ordered.removeIf(o -> o.getId() == orderId);

        //return the ordered list into session
        session.setAttribute("ordered", ordered);

        //Updating order status in db
        try {
            dao.changeOrderStage(orderId, "kitchen");
        } catch (DAOException e) {
            LOG.error("Can`t change order state", e);
            return "/error";
        }

        return "/manager-panel";
    }
}
