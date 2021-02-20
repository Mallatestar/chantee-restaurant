package com.restaurant.chantee.model.dao;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.Exception.NoSuchEntityException;
import com.restaurant.chantee.model.domain.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.restaurant.chantee.model.dao.ConnectionPool.LOG;

/**
 * DAO to processing all actions with product elements
 */
public class ProductDAO {

    private static ProductDAO instance;
    private ConnectionPool cp = ConnectionPool.getInstance();

    public void setCp(ConnectionPool cp) {
        this.cp = cp;
    }

    static {
        instance = new ProductDAO();
    }

    public static ProductDAO getInstance(){
        if (instance == null){
            instance = new ProductDAO();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return cp.getConnection();
    }

    /**
     * Finding all products with this category
     * @param category of product from UI
     * @return list of product objects
     * @throws DAOException if will be some problems
     */
    public List<Product> getAllCategoryProducts(String category) throws DAOException {
        LOG.debug("Called getAllCategoryProducts(" + category + ")");
        List<Product> result = new LinkedList<>();

        Connection connection = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        ResultSet res1 = null;
        ResultSet res2 = null;

        try {
            connection = instance.getConnection();
            prep1 = connection.prepareStatement(SQL.FIND_CATEGORY.getQuery());
            prep1.setString(1, category);
            res1 = prep1.executeQuery();
            int categoryID = 0;
            while (res1.next()){
                categoryID = res1.getInt("id");
            }
            LOG.debug("Result of FIND_CATEGORY:" + categoryID);
            prep2 = connection.prepareStatement(SQL.FIND_PRODUCTS_BY_CATEGORY.getQuery());
            prep2.setInt(1, categoryID);
            res2 = prep2.executeQuery();
            while (res2.next()){
                Product product = new Product();
                product.setId(res2.getInt("id"));
                product.setTitle(res2.getString("title"));
                product.setPrice(res2.getInt("price"));
                product.setDescription(res2.getString("description"));
                product.setImg_path(res2.getString("img_path"));
                product.setCategory(categoryID);
                result.add(product);
            }
        } catch (SQLException e) {
            LOG.error("getAllCategoryProducts error in SQL", e);
            throw new DAOException();
        } finally {
            closeQuietly(res1);
            closeQuietly(res2);
            closeQuietly(prep1);
            closeQuietly(prep2);
            closeQuietly(connection);
        }
        LOG.debug("Result of getAllCategoryProducts" + result);
        return result;
    }

    /**
     * @param id of product which will be find
     * @return product object
     * @throws NoSuchEntityException if there is no product with such title
     * @throws DAOException if will be some problems
     */
    public Product findProductById(int id) throws NoSuchEntityException, DAOException {
        Product product = new Product();
        product.setId(id);
        Connection connection = null;
        PreparedStatement prep = null;
        ResultSet res = null;
        try {
            connection = instance.getConnection();
            prep = connection.prepareStatement(SQL.FIND_PRODUCT_BY_ID.getQuery());
            prep.setInt(1, id);
            res = prep.executeQuery();
            if (res.next()){
                product.setTitle(res.getString("title"));
                product.setPrice(res.getInt("price"));
                product.setDescription(res.getString("description"));
                product.setImg_path(res.getString("img_path"));
                product.setCategory(res.getInt("category"));
            }else {
                throw new NoSuchEntityException();
            }
        } catch (SQLException e) {
            LOG.error("Some problems with connection", e);
            throw new DAOException();
        }finally {
            closeQuietly(res);
            closeQuietly(prep);
            closeQuietly(connection);
        }
        return product;
    }

    /**
     * Just for usability
     * @param connection Connection obj
     */
    static void closeQuietly(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error("closeQuietly CONNECTION");
        }
    }

    /**
     * Just for usability
     * @param res ResultSet obj
     */
    static void closeQuietly(ResultSet res){
        try {
            if (res != null){
                res.close();
            }
        } catch (SQLException e) {
            LOG.error("closeQuietly RES");
        }
    }

    /**
     * Just for usability
     * @param prep PreparedStatment obj
     */
    static void closeQuietly(PreparedStatement prep){
        try {
            if (prep != null){
                prep.close();
            }
        } catch (SQLException e) {
            LOG.error("closeQuietly PREP");
        }
    }

    public List<Product> getAllProducts() {
        List<Product> list = new LinkedList<>();
        Connection connection = null;
        PreparedStatement prep = null;
        ResultSet res = null;

        try {
            connection = instance.getConnection();
            prep = connection.prepareStatement(SQL.FIND_ALL_PRODUCTS.getQuery());
            res = prep.executeQuery();
            while (res.next()){
                Product product = new Product();
                product.setId(res.getInt("id"));
                product.setTitle(res.getString("title"));
                product.setPrice(res.getInt("price"));
                product.setDescription(res.getString("description"));
                product.setImg_path(res.getString("img_path"));
                product.setCategory(res.getInt("category"));
                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeQuietly(res);
            closeQuietly(prep);
            closeQuietly(connection);
        }
        return list;
    }
}
