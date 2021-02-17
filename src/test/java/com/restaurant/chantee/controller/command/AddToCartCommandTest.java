package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.Exception.NoSuchEntityException;
import com.restaurant.chantee.model.dao.ProductDAO;
import com.restaurant.chantee.model.domain.entity.Product;
import com.restaurant.chantee.model.domain.entity.ShoppingCart;
import com.restaurant.chantee.model.domain.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AddToCartCommandTest {

    @Mock
    private static HttpServletRequest request;
    @Mock
    private static HttpServletResponse response;
    @Mock
    private static HttpSession session;
    @Mock
    private static ProductDAO dao;

    @InjectMocks
    public AddToCartCommand testCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testCommand.setDao(dao);
        when(request.getSession()).thenReturn(session);
    }


    @Test
    public void execute1() {
        assertEquals(testCommand.execute(request, response), "/register.jsp");
    }

    @Test
    public void execute2() throws DAOException, NoSuchEntityException {
        when(session.getAttribute("user")).thenReturn(new User());
        when(request.getParameter("productTitle")).thenReturn("TestProduct");
        when(request.getParameter("productQuantity")).thenReturn("1");
        when(dao.findProductByTitle("TestProduct")).thenReturn(new Product());
        when(session.getAttribute("cart")).thenReturn(new ShoppingCart());
        assertEquals(testCommand.execute(request, response), "/menu");
    }

}