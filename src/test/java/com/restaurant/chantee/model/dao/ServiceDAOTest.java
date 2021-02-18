package com.restaurant.chantee.model.dao;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.domain.entity.DeliveryData;
import com.restaurant.chantee.model.domain.entity.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.naming.NamingException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static com.restaurant.chantee.model.dao.UserDAOTest.LOG;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

public class ServiceDAOTest {
    @Mock
    private static ConnectionPool CP;

    private static final ServiceDAO test = ServiceDAO.getInstance();
    private static final int userId = 1;
    private static final String dateTime = "TestTime";
    private static final String comm = "TestComment";
    private static final DeliveryData dd = new DeliveryData();
    private static List<Order> ordered;
    @Before
    public void setUp() throws SQLException, NamingException {
        MockitoAnnotations.initMocks(this);
        Connection connection = DriverManager.getConnection(getURL());
        doReturn(connection).when(CP).getConnection();
        test.setCp(CP);
        dd.setAddress("testAddress");
        dd.setUser_id(1);
        dd.setPhone("testPhone");
    }

    static String getURL(){
        String URL = null;
        FileInputStream fis = null;
        Properties prop = new Properties();
        try {
            fis = new FileInputStream("src/test/resources/testdb.properties");
            prop.load(fis);
            URL = prop.getProperty("URL");
        } catch (FileNotFoundException e) {
            LOG.error("Can`t find properties");
        } catch (IOException e) {
            LOG.error("Can`t load properties");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                LOG.error("Can`t close FIS");
            }
        }
        return URL;
    }

    @Test
    public void getConnection() throws SQLException {
        assertTrue(test.getConnection().isValid(10));
    }

    @Test
    public void registerOrder() throws DAOException {
        test.registerOrder(userId, dateTime, comm, dd);
    }

    @Test
    public void registerReceipt() {
    }

    @Test
    public void getAllOnStage() throws DAOException {
        ordered = test.getAllOnStage("ordered");
    }

    @Test
    public void getCartByOrderId() {
    }

    @Test
    public void changeOrderStage() throws DAOException {
        Order order = ordered.get(0);
        test.changeOrderStage(order.getId(), "kitchen");
    }

    @Test
    public void dropOrderById() throws DAOException {
        Order order = ordered.get(0);
        test.dropOrderById(order.getId());
    }
}