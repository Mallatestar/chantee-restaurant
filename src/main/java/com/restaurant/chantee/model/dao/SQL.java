package com.restaurant.chantee.model.dao;

public enum SQL {
    //Service operations
    FIND_CATEGORY("SELECT id FROM categories WHERE name = ?;"),
    FIND_PRODUCTS_BY_CATEGORY("SELECT * FROM products WHERE category = ?;"),
    FIND_PRODUCT_BY_TITLE("SELECT * FROM products WHERE title = ?"),


    //User operations
    ADD_USER("INSERT INTO users (username, email, user_password) VALUES (?, ?, ?)"),
    FIND_ALL_USERS("SELECT * FROM users;"),
    FIND_USER("SELECT * FROM users WHERE email = ?;"),
    DELETE_USER("");


    private final String query;

    SQL(String query){
        this.query = query;
    }

    public String getQuery(){
        return query;
    }
}
