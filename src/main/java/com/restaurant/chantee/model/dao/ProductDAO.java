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

public class ProductDAO {

    private static ProductDAO instance;

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
        return ConnectionPool.getInstance().getConnection();
    }


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

    public Product findProductByTitle(String title) throws NoSuchEntityException, DAOException {
        if (title == null){
            throw new DAOException();
        }
        Product product = new Product();
        product.setTitle(title);
        Connection connection = null;
        PreparedStatement prep = null;
        ResultSet res = null;
        try {
            connection = instance.getConnection();
            prep = connection.prepareStatement(SQL.FIND_PRODUCT_BY_TITLE.getQuery());
            prep.setString(1, title);
            res = prep.executeQuery();
            if (res.next()){
                product.setId(res.getInt("id"));
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

    static void closeQuietly(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error("closeQuietly CONNECTION");
        }
    }

    static void closeQuietly(ResultSet res){
        try {
            if (res != null){
                res.close();
            }
        } catch (SQLException e) {
            LOG.error("closeQuietly RES");
        }
    }

    static void closeQuietly(PreparedStatement prep){
        try {
            if (prep != null){
                prep.close();
            }
        } catch (SQLException e) {
            LOG.error("closeQuietly PREP");
        }
    }
}
