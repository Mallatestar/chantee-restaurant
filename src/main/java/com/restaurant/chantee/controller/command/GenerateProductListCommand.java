package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ProductDAO;
import com.restaurant.chantee.model.domain.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

public class GenerateProductListCommand implements Command{


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Called execute() in GenerateProductListCommand");
        List<Product> products = null;
        try {
            products = ProductDAO.getAllCategoryProducts(request.getParameter("category"));
        } catch (DAOException e) {
            LOG.error("Problem with GenerateProd in DAO", e);
        }
        request.getSession().setAttribute("products", products);
        LOG.debug("Result of execute() in GenerateProductListCommand:" + products);
        if (products != null && products.isEmpty()){
            return "/error.jsp";
        }
        return "/category-page.jsp";
    }
}
