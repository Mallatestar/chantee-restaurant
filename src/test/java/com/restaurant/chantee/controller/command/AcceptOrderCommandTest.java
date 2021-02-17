package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.ServiceDAO;
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
import static org.mockito.Mockito.*;

public class AcceptOrderCommandTest {
    @Mock
    private static HttpServletRequest request;
    @Mock
    private static HttpServletResponse response;
    @Mock
    private static HttpSession session;
    @Mock
    private static ServiceDAO dao;

    @InjectMocks
    public AcceptOrderCommand testCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testCommand.setDao(dao);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("orderId")).thenReturn("1");
        when(session.getAttribute("ordered")).thenReturn(new LinkedList<>());
    }

    @Test
    public void execute1() throws DAOException {
        doNothing().when(dao).changeOrderStage(1, "kitchen");
        assertEquals(testCommand.execute(request, response), "/manager-panel");
    }

    @Test
    public void execute2() throws DAOException {
        doThrow(new DAOException()).when(dao).changeOrderStage(1, "kitchen");
        assertEquals(testCommand.execute(request, response), "/error");
    }
}