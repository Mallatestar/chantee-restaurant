package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.Exception.NoSuchEntityException;
import com.restaurant.chantee.model.dao.ProductDAO;
import com.restaurant.chantee.model.domain.entity.Product;
import com.restaurant.chantee.model.domain.entity.ShoppingCart;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RemoveProductCommandTest {

    @Mock
    private static HttpServletRequest request;
    @Mock
    private static HttpServletResponse response;
    @Mock
    private static HttpSession session;
    @Mock
    private static ProductDAO dao;

    @InjectMocks
    public RemoveProductCommand testCommand;

    @Before
    public void setUp() throws DAOException, NoSuchEntityException {
        MockitoAnnotations.initMocks(this);
        testCommand.setDao(dao);
        when(request.getSession()).thenReturn(session);
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product();
        product.setTitle("TestProduct");
        cart.addProduct(product, 1);
        when(session.getAttribute("cart")).thenReturn(cart);
        when(request.getParameter("productTitle")).thenReturn("TestProduct");
        when(dao.findProductByTitle("TestProduct")).thenReturn(product);
    }

    @Test
    public void execute() {
        assertEquals(testCommand.execute(request, response), "/cart.jsp");
    }
}