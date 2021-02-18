package com.restaurant.chantee.model.dao;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.Exception.LoginException;
import com.restaurant.chantee.model.Exception.NoSuchEntityException;
import com.restaurant.chantee.model.domain.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

public class UserDAOTest {
    static final Logger LOG = LogManager.getLogger("TESTS");
    @Mock
    private static ConnectionPool CP;

    private static final UserDAO test = UserDAO.getInstance();

    private static String username = "TestUsername";
    private static final Random random = new Random();
    private static final int salt = random.nextInt();
    private static final String email = "test@test.test";
    private static String pass = "TestPassword";
    private static int testId;

    @Before
    public void setUp() throws SQLException, NamingException {
        MockitoAnnotations.initMocks(this);
        Connection connection = DriverManager.getConnection(getURL());
        doReturn(connection).when(CP).getConnection();
        test.setCp(CP);
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
    public void createUser() throws DAOException {
        User user1 = new User();
        user1.setUsername(username);
        user1.setEmail(email + salt);
        user1.setUser_password(pass);
        User user2 = test.createUser(username, email + salt, pass);
        user1.setId(user2.getId());
        assertEquals(user1, user2);
    }

    @Test
    public void authenticateUser() throws LoginException, DAOException {
        User user1 = new User();
        user1.setUsername("root");
        user1.setEmail("bengijha@gmail.com");
        user1.setUser_password("0CD3ABB80C5D017FBB3FDA4A2B6069528BE2960107936BF066717432D216E656");
        User user2 = test.authenticateUser(user1.getEmail(), user1.getUser_password());
        testId = user2.getId();
        user1.setId(testId);
        assertEquals(user1, user2);
    }

    @Test
    public void findUserByEmail() throws LoginException, DAOException, NoSuchEntityException {
        User user1 = new User();
        user1.setUsername("root");
        user1.setEmail("bengijha@gmail.com");
        user1.setUser_password("0CD3ABB80C5D017FBB3FDA4A2B6069528BE2960107936BF066717432D216E656");
        User user2 = test.findUserByEmail(user1.getEmail());
        user1.setId(user2.getId());
        assertEquals(user1, user2);
    }

    @Test
    public void getAllManagers() throws DAOException {
        List<Integer> list = test.getAllManagers();
        assertEquals(1, list.size());

    }

    @Test
    public void renameUser() throws DAOException, NoSuchEntityException {
        username = "NewName";
        test.renameUser(username, testId);
        assertTrue(true);

    }

    @Test
    public void changePass() throws DAOException {
        pass = "NewPass";
        test.changePass(pass, testId);
        assertTrue(true);
    }

}