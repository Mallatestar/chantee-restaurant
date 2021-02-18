package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ProductDAO;
import com.restaurant.chantee.model.domain.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static com.restaurant.chantee.controller.command.CommandPool.LOG;

/**
 * Command which automatically generated List of products for UI
 */
public class GenerateProductListCommand implements Command{
    private static ProductDAO dao = ProductDAO.getInstance();
    void setDao(ProductDAO newDAO){
        dao = newDAO;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Called execute() in GenerateProductListCommand");
        request.getSession().setAttribute("isAll", false);
        List<Product> products = null;

        //Getting category name from UI
        String category = request.getParameter("category");

        //Generating a product list from DB
        try {
            if (category.equals("all")){
                products = dao.getAllProducts();
                request.getSession().setAttribute("isAll", true);
            }else {
                products = dao.getAllCategoryProducts(category);
            }
        } catch (DAOException e) {
            LOG.error("Problem with GenerateProd in DAO", e);
        }

        //Validate the product list
        if (products != null && products.isEmpty()){
            return "/error";
        }

        //If sorting param exist -- sort the product list
        String sortParam = request.getParameter("sortParam");
        if (sortParam != null) {
            switch (sortParam) {
                case "byName":
                    LOG.debug("Sort list by name");
                    products = products.stream()
                            .sorted(new Product.CompareByName())
                            .collect(Collectors.toList());
                    break;

                case "byPriceDescending":
                    LOG.debug("Sort list by PriceDescending");
                    products = products.stream()
                            .sorted(new Product.CompareByPrice())
                            .collect(Collectors.toList());
                    Collections.reverse(products);
                    break;

                case "byPriceAscending":
                    LOG.debug("Sort list by PriceAscending");
                    products = products.stream()
                            .sorted(new Product.CompareByPrice())
                            .collect(Collectors.toList());
                    break;
            }
        }

        //Creating a map<page number, product list> for pagination
        Map<Integer, List<Product>> pageMap = new TreeMap<>();
        //Fill the pagination map
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

        //Updating session
        request.getSession().setAttribute("category", request.getParameter("category"));
        request.getSession().setAttribute("productMap", pageMap);
        request.getSession().setAttribute("categoryPageNumber", 1);
        LOG.debug("Result of execute() in GenerateProductListCommand:" + products);
        return "/menu";
    }
}
