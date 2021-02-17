package com.restaurant.chantee.model.dao;

public enum SQL {
    //Product operations
    FIND_CATEGORY("SELECT id FROM categories WHERE name = ?;"),
    FIND_PRODUCTS_BY_CATEGORY("SELECT * FROM products WHERE category = ?;"),
    FIND_PRODUCT_BY_TITLE("SELECT * FROM products WHERE title = ?"),


    //User operations
    ADD_USER("INSERT INTO users (username, email, user_password) VALUES (?, ?, ?)"),
    FIND_ALL_USERS("SELECT * FROM users;"),
    FIND_USER("SELECT * FROM users WHERE email = ?;"),
    RENAME_USER("UPDATE users SET username = ? WHERE id = ?;"),
    CHANGE_PASSWORD("UPDATE users SET user_password = ? WHERE id = ?;"),


    //Service operations
    REGISTER_ORDER("INSERT INTO orders(user_id, date_time, comm, delivery_address, phone) " +
            "VALUES (?, ?, ?, ?, ?)"),
    FILL_RECEIPT("INSERT INTO order_products(order_id, product_id, product_quantity)" +
            " VALUES (?, ? , ?)"),
    GET_ALL_ORDERS_BY_STAGE("SELECT * FROM orders WHERE stage = ?;"),
    GET_CART_BY_ORDER_ID("SELECT * FROM order_products INNER JOIN products p ON order_products.product_id = p.id WHERE order_id = ?;"),
    CHANGE_ORDER_STAGE("UPDATE orders SET stage = ? WHERE id = ?;"),
    DROP_ORDER_BY_ID("DELETE FROM orders WHERE id = ?;");


    private final String query;

    SQL(String query){
        this.query = query;
    }

    public String getQuery(){
        return query;
    }
}
