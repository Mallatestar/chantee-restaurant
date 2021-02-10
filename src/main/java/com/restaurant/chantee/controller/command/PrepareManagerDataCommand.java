package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ServiceDAO;
import com.restaurant.chantee.model.domain.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;
import static com.restaurant.chantee.model.dao.ServiceDAO.getCartByOrderId;

public class PrepareManagerDataCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orderedTmp;
        List<Order> kitchenTmp;
        List<Order> deliveryTmp;
        List<Order> ordered = null;
        List<Order> kitchen = null;
        List<Order> delivery = null;
        try {
            orderedTmp = ServiceDAO.getAllOnStage("ordered");
            LOG.debug("orderedTmp orders list after DAO:" + orderedTmp);

            kitchenTmp = ServiceDAO.getAllOnStage("kitchen");
            LOG.debug("kitchenTmp orders list after DAO:" + kitchenTmp);

            deliveryTmp = ServiceDAO.getAllOnStage("delivery");
            LOG.debug("deliveryTmp orders list after DAO:" + deliveryTmp);

            ordered = getCartByOrderId(orderedTmp);
            LOG.debug("Ordered orders list after DAO:" + ordered);

            kitchen = getCartByOrderId(kitchenTmp);
            LOG.debug("kitchen orders list after DAO:" + kitchen);

            delivery = getCartByOrderId(deliveryTmp);
            LOG.debug("delivery orders list after DAO:" + delivery);

        } catch (DAOException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute("ordered", ordered);
        session.setAttribute("kitchen", kitchen);
        session.setAttribute("delivery", delivery);

        return "/manager.jsp";
    }
}
