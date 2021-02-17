package com.restaurant.chantee.controller.command;

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

public class NextPageCommandTest {

    @Mock
    private static HttpServletRequest request;
    @Mock
    private static HttpServletResponse response;
    @Mock
    private static HttpSession session;

    @InjectMocks
    public NextPageCommand testCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("categoryPageNumber")).thenReturn(1);
    }

    @Test
    public void execute(){
        String execute = testCommand.execute(request, response);
        assertEquals(execute, "/menu");
    }
}