package com.restaurant.chantee;

import com.restaurant.chantee.controller.command.AcceptOrderCommand;
import com.restaurant.chantee.model.dao.ConnectionPool;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class CommandTest {
    @Mock
    static HttpServletRequest request;
    @Mock
    static HttpServletResponse response;
    @Mock
    static HttpSession session;

    @Before
    public void setUp() throws SQLException {
        when(request.getParameter("orderId")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(ConnectionPool.getInstance().getConnection()).thenReturn(DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb?user=root&password=Benjha01!&serverTimezone=UTC"));
    }

    @Test
    public void acceptOrderTest(){
        AcceptOrderCommand aoc = new AcceptOrderCommand();
        String result = aoc.execute(request, response);
        assertEquals(result, "/manager.jsp");
    }

}