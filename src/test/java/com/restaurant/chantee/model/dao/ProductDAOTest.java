package com.restaurant.chantee.model.dao;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.Exception.NoSuchEntityException;
import com.restaurant.chantee.model.domain.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static com.restaurant.chantee.model.dao.UserDAOTest.getURL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

public class ProductDAOTest {
    @Mock
    private static ConnectionPool CP;
    private static final ProductDAO test = ProductDAO.getInstance();

    @Before
    public void setUp() throws SQLException, NamingException {
        MockitoAnnotations.initMocks(this);
        Connection connection = DriverManager.getConnection(getURL());
        doReturn(connection).when(CP).getConnection();
        test.setCp(CP);
    }

    @Test
    public void getConnection() throws SQLException {
        assertTrue(test.getConnection().isValid(10));
    }

    @Test
    public void getAllCategoryProducts() throws DAOException {
        List<Product> list = test.getAllCategoryProducts("cakes");
        assertEquals(list.size(), 8);
    }

    @Test
    public void findProductByTitle() throws DAOException, NoSuchEntityException {
        Product product1 = new Product();
        product1.setTitle("Малина-шоколад");
        Product product2 = test.findProductByTitle(product1.getTitle());
        product1.setId(product2.getId());
        assertEquals(product1, product2);
    }
}