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
 * Command which finished restaurant work cycle and save the order into DB
 */
public class OrderFinishedCommand implements Command {
    private static ServiceDAO dao = ServiceDAO.getInstance();
    void setDao(ServiceDAO newDAO){
        dao = newDAO;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //Getting order id
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        //Getting and updating list of orders in delivery
        List<Order> delivery = (List<Order>) session.getAttribute("delivery");
        session.removeAttribute("delivery");
        delivery.removeIf(o -> o.getId() == orderId);
        session.setAttribute("delivery", delivery);

        //Saving the order into db
        try {
            dao.changeOrderStage(orderId, "finished");
        } catch (DAOException e) {
            LOG.error("Can`t change order state", e);
            return "/error";
        }

        return "/manager-panel";
    }
}
