package com.restaurant.chantee.model.domain.entity;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<Product, Integer> cart;

    public ShoppingCart(){
        cart = new HashMap<>();
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void addProduct(Product product, Integer quantity){
        if (product != null && quantity != null && quantity >= 0){
            cart.put(product, quantity);
        }
    }

    public void removeProduct(Product product){
        if (product != null && cart.containsKey(product)){
            cart.remove(product);
        }
    }

    public void changeQuantity(Product product, Integer newQuantity){
        if (product != null && newQuantity != null && newQuantity >= 0){
            cart.remove(product);
            cart.put(product, newQuantity);
        }
    }
}
