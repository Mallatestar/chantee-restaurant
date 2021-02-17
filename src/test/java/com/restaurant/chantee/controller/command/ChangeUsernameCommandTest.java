package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.UserDAO;
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
import static org.mockito.Mockito.*;

public class ChangeUsernameCommandTest {

    @Mock
    private static HttpServletRequest request;
    @Mock
    private static HttpServletResponse response;
    @Mock
    private static HttpSession session;
    @Mock
    private static UserDAO dao;
    @Mock
    private static User user;

    @InjectMocks
    public ChangeUsernameCommand testCommand;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        testCommand.setDao(dao);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("username")).thenReturn("TestName");
        when((session.getAttribute("user"))).thenReturn(user);
        when(user.getId()).thenReturn(1);
    }

    @Test
    public void execute1() throws DAOException {
        doNothing().when(dao).renameUser("TestName", 1);
        String res = testCommand.execute(request, response);
        assertEquals(res, "/account");
    }

    @Test
    public void execute2() throws DAOException {
        doThrow(new DAOException()).when(dao).renameUser("TestName", 1);
        String res = testCommand.execute(request, response);
        assertEquals(res, "/error");
    }
}