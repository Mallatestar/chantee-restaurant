package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ServiceDAO;
import com.restaurant.chantee.model.domain.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

public class ToDeliveryCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        List<Order> kitchen = (List<Order>) session.getAttribute("kitchen");

        session.removeAttribute("kitchen");
        kitchen.removeIf(o -> o.getId() == orderId);
        session.setAttribute("kitchen", kitchen);

        try {
            ServiceDAO.changeOrderStage(orderId, "delivery");
        } catch (DAOException e) {
            LOG.error("Can`t change order state", e);
            return "/error.jsp";
        }

        return "/manager.jsp";
    }
}
