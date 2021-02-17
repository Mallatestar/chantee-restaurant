package com.restaurant.chantee.controller.command;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ChangeLocaleCommandTest {

    @Mock
    private static HttpServletRequest request;
    @Mock
    private static HttpServletResponse response;
    @Mock
    private static HttpSession session;

    @Test
    public void execute1() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("locale")).thenReturn("ru");
        ChangeLocaleCommand comm = new ChangeLocaleCommand();
        String res = comm.execute(request, response);
        assertEquals(res, "/home");
    }
    @Test
    public void execute2() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("locale")).thenReturn("en");
        ChangeLocaleCommand comm = new ChangeLocaleCommand();
        String res = comm.execute(request, response);
        assertEquals(res, "/home");
    }
}