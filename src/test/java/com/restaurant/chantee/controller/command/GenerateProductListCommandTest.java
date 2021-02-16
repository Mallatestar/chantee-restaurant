package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ProductDAO;
import com.restaurant.chantee.model.domain.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class GenerateProductListCommandTest {

    @Mock
    private static HttpServletRequest request;
    @Mock
    private static HttpServletResponse response;
    @Mock
    private static HttpSession session;
    @Mock
    private static ProductDAO dao;

    @InjectMocks
    public GenerateProductListCommand testCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testCommand.setDao(dao);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void execute() throws DAOException {
        when(request.getParameter("category")).thenReturn("cake");
        when(dao.getAllCategoryProducts("cake")).thenReturn(new LinkedList<Product>());
        String res = testCommand.execute(request, response);
        assertEquals(res, "/error.jsp");
    }
}