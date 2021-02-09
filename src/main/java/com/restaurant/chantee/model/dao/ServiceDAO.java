package com.restaurant.chantee.model.dao;

import com.restaurant.chantee.model.Exception.DAOException;
import com.restaurant.chantee.model.domain.entity.DeliveryData;
import com.restaurant.chantee.model.domain.entity.Order;
import com.restaurant.chantee.model.domain.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.restaurant.chantee.model.dao.ConnectionPool.LOG;
import static com.restaurant.chantee.model.dao.ProductDAO.closeQuietly;

public class ServiceDAO {

    public static Order registerOrder(int userId, String date_time, String comment, DeliveryData deliveryData) throws DAOException {
        Order order = new Order();
        Connection connection = null;
        PreparedStatement prep1 = null;
        PreparedStatement prep2 = null;
        ResultSet res = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();

            prep1 = connection.prepareStatement(SQL.REGISTER_ORDER.getQuery());
            prep1.setInt(1, userId);
            prep1.setString(2, date_time);
            prep1.setString(3, comment);
            prep1.setString(4, deliveryData.getAddress());
            prep1.setString(5, deliveryData.getPhone());
            prep1.executeUpdate();

            prep2 = connection.prepareStatement("SELECT * FROM orders WHERE date_time = ? && orders.user_id = ?;");
            prep2.setString(1, date_time);
            prep2.setInt(2, userId);
            res = prep2.executeQuery();
            if (res.next()){
                order.setId(res.getInt("id"));
                order.setUser_id(res.getInt("user_id"));
                order.setDateTime(res.getString("date_time"));
                order.setComment(res.getString("comm"));
                order.setDelivery_address(res.getString("delivery_address"));
                order.setPhone(res.getString("phone"));
            }else {
                LOG.error("Can`t find order" + res.toString());
                throw new DAOException();
            }

        } catch (SQLException e) {
            LOG.error("Can`t get connection in registerOrder", e);
            throw new DAOException();
        }finally {
            closeQuietly(res);
            closeQuietly(prep1);
            closeQuietly(prep2);
            closeQuietly(connection);
        }

        return order;
    }


    static void switchAutoCommit(Connection connection) throws DAOException {
        try {
            boolean state = connection.getAutoCommit();
            connection.setAutoCommit(!state);
        } catch (SQLException e) {
            LOG.error("Can`t change autoCommit mode");
            throw new DAOException();
        }
    }

    public static void registerReceipt(Map<Product, Integer> cart, int orderId) throws DAOException {

        LOG.debug("registerReceipt(" + cart + " , " + orderId + ")");

        Connection connection = null;
        PreparedStatement prep = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            switchAutoCommit(connection);
            prep = connection.prepareStatement(SQL.FILL_RECEIPT.getQuery());

            for (Product p : cart.keySet()){
                prep.setInt(1, orderId);
                prep.setInt(2, p.getId());
                prep.setInt(3, cart.get(p));
                prep.executeUpdate();
                connection.commit();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException throwables) {
                LOG.fatal("Can`t rollback connection in registerReceipt", throwables);
            }
            LOG.error("Can`t get connection in registerReceipt", e);
            throw new DAOException();
        }finally {
            switchAutoCommit(connection);
            closeQuietly(prep);
            closeQuietly(connection);
        }
    }


    public static List<Order> getAllOnStage(String stage) throws DAOException {
        List<Order> stageList = new LinkedList<>();
        Connection connection = null;
        PreparedStatement prep = null;
        ResultSet res = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            prep = connection.prepareStatement(SQL.GET_ALL_ORDERS_BY_STAGE.getQuery());
            prep.setString(1, stage);
            res = prep.executeQuery();
            while (res.next()){
                Order order = new Order();
                order.setId(res.getInt("id"));
                order.setDateTime(res.getString("date_time"));
                order.setComment(res.getString("comm"));
                order.setDelivery_address(res.getString("delivery_address"));
                order.setPhone(res.getString("phone"));
                //TODO: Нужен джоин по трем таблицам : заказ + чек + товары.
                // Из него вытащить корзину, а из нее посчитать тотал прайс
                // + Нужно придумать лучше организацию симпл полей, что в них выводить, скорее всего:
                // всю инфу ордера, но без корзины
            }

        } catch (SQLException e) {
            LOG.error("CAn`t get connection in getAllOnStage", e);
            throw new DAOException();
        }

        return stageList;
    }
}
