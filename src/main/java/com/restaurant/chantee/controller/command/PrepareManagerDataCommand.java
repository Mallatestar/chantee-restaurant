package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ServiceDAO;
import com.restaurant.chantee.model.domain.entity.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PrepareManagerDataCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> ordered = null;
        List<Order> kitchen = null;
        List<Order> delivery = null;
        try {
            ordered = ServiceDAO.getAllOnStage("ordered");
            kitchen = ServiceDAO.getAllOnStage("kitchen");
            delivery = ServiceDAO.getAllOnStage("delivery");
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
