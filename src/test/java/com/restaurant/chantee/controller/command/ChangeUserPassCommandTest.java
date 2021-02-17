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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ChangeUserPassCommandTest {

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
    public ChangeUserPassCommand testCommand;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        testCommand.setDao(dao);
        when(request.getSession()).thenReturn(session);
        when((session.getAttribute("user"))).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(request.getParameter("oldPassword")).thenReturn("Password");
        when(request.getParameter("newPassword")).thenReturn("Password");
    }

    @Test
    public void execute1() throws DAOException {
        doNothing().when(dao).changePass("E7CF3EF4F17C3999A94F2C6F612E8A888E5B1026878E4E19398B23BD38EC221A", 1);
        String res = testCommand.execute(request, response);
        assertEquals(res, "/account");
    }

}