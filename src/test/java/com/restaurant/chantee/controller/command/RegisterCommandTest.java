package com.restaurant.chantee.controller.command;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.dao.UserDAO;
import com.restaurant.chantee.model.domain.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterCommandTest {
    @Mock
    private static HttpServletRequest request;
    @Mock
    private static HttpServletResponse response;
    @Mock
    private static HttpSession session;
    @Mock
    private static UserDAO dao;

    @InjectMocks
    public RegisterCommand testCommand;

    @Before
    public void setUp1() throws DAOException{
        MockitoAnnotations.initMocks(this);
        testCommand.setDao(dao);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("username")).thenReturn("Test");
        when(request.getParameter("email")).thenReturn("test@mail.test");
        when(request.getParameter("user_password")).thenReturn("testTest");
        when(dao.createUser("Test", "test@mail.test", "C53E74B1A538B7218ECD6638F0431A5D223B06FF3D849267DF6B1A81A32E273A")).thenReturn(new User());
    }

    @Test
    public void execute1() {
        assertEquals(testCommand.execute(request, response), "/home");
    }

    @Test
    public void execute2() {
        when(request.getParameter("username")).thenReturn("Test");
        when(request.getParameter("email")).thenReturn("teest");
        when(request.getParameter("user_password")).thenReturn("testTest");
        assertEquals(testCommand.execute(request, response), "/sign-up");
    }
}