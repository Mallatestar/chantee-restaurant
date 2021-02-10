package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ProductDAO;
import com.restaurant.chantee.model.domain.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        if (products != null && products.isEmpty()){
            return "/error.jsp";
        }

        Map<Integer, List<Product>> pageMap = new LinkedHashMap<>();

        int counter = 1;
        List<Product> pageProducts = new LinkedList<>();
        for (Product p : products){
            pageProducts.add(p);
            if (pageProducts.size() == 4){
                pageMap.put(counter, pageProducts);
                counter++;
                pageProducts = new LinkedList<>();
            }
        }
        pageMap.put(counter, pageProducts);

        request.getSession().setAttribute("productMap", pageMap);
        request.getSession().setAttribute("categoryPageNumber", 1);
        LOG.debug("Result of execute() in GenerateProductListCommand:" + products);
        return "/category-page.jsp";
    }
}
