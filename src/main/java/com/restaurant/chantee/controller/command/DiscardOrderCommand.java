package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ServiceDAO;
import com.restaurant.chantee.model.domain.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

public class DiscardOrderCommand implements Command{
    private static ServiceDAO dao = ServiceDAO.getInstance();
    void setDao(ServiceDAO newDAO){
        dao = newDAO;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        List<Order> ordered = (List<Order>) session.getAttribute("ordered");

        session.removeAttribute("ordered");
        ordered.removeIf(o -> o.getId() == orderId);
        session.setAttribute("ordered", ordered);

        try {
            dao.dropOrderById(orderId);
        } catch (DAOException e) {
            LOG.error("Can`t change order state", e);
            return "/error";
        }

        return "/manager-panel";
    }
}
