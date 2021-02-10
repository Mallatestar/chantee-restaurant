package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ServiceDAO;
import com.restaurant.chantee.model.domain.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

public class OrderFinishedCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        List<Order> delivery = (List<Order>) session.getAttribute("delivery");

        session.removeAttribute("delivery");
        delivery.removeIf(o -> o.getId() == orderId);
        session.setAttribute("delivery", delivery);

        try {
            ServiceDAO.changeOrderStage(orderId, "finished");
        } catch (DAOException e) {
            LOG.error("Can`t change order state", e);
            return "/error.jsp";
        }

        return "/manager.jsp";
    }
}
