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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PrepareManagerDataCommandTest {

    @Mock
    private static HttpServletRequest request;
    @Mock
    private static HttpServletResponse response;
    @Mock
    private static HttpSession session;
    @Mock
    private static ServiceDAO dao;

    private static List list = new LinkedList();

    @InjectMocks
    public PrepareManagerDataCommand testCommand;

    @Before
    public void setUp() throws DAOException {
        MockitoAnnotations.initMocks(this);
        testCommand.setDao(dao);
        when(request.getSession()).thenReturn(session);
        doReturn(list).when(dao).getAllOnStage("ordered");
        doReturn(list).when(dao).getAllOnStage("kitchen");
        doReturn(list).when(dao).getAllOnStage("delivery");

    }

    @Test
    public void execute1() throws DAOException {
        doReturn(new LinkedList<>()).when(dao).getCartByOrderId(list);
        assertEquals(testCommand.execute(request, response), "/manager-panel");
    }

    @Test
    public void execute2() throws DAOException {
        doThrow(new DAOException()).when(dao).getCartByOrderId(list);
        assertEquals(testCommand.execute(request, response), "/error");
    }
}