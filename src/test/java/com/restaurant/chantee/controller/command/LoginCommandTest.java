package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.Exception.LoginException;
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
import static org.mockito.Mockito.when;

public class LoginCommandTest {
    @Mock
    private static HttpServletRequest request;
    @Mock
    private static HttpServletResponse response;
    @Mock
    private static HttpSession session;
    @Mock
    private static UserDAO dao;

    @InjectMocks
    public LoginCommand testCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testCommand.setDao(dao);
        when(request.getSession()).thenReturn(session);

    }


    @Test
    public void execute1() throws LoginException, DAOException {
        when(request.getParameter("email")).thenReturn("bengijha@gmail.com");
        when(request.getParameter("user_password")).thenReturn("admin");
        when(dao.authenticateUser("bengijha@gmail.com", "8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918")).thenReturn(new User());
        assertEquals(testCommand.execute(request, response), "/index.jsp");
    }

    @Test
    public void execute2() throws LoginException, DAOException {
        when(request.getParameter("email")).thenReturn("ben@gmail.com");
        when(request.getParameter("user_password")).thenReturn("admin");
        when(dao.authenticateUser("ben@gmail.com", "8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918")).thenThrow(new LoginException());
        String res = testCommand.execute(request, response);
        assertEquals(res, "/login.jsp");
    }

    @Test
    public void execute3() throws LoginException, DAOException {
        when(request.getParameter("email")).thenReturn("benmail.com");
        when(request.getParameter("user_password")).thenReturn("admin");
        String res = testCommand.execute(request, response);
        assertEquals(res, "/error.jsp");
    }
}