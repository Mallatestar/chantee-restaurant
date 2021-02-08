package com.restaurant.chantee.model.domain.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShoppingCart implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart);
    }

    @Override
    public String toString() {
        StringBuilder asText = new StringBuilder();
        for (Product p : cart.keySet()){
            asText.append(p).append("-->").append(cart.get(p));
        }
        String res = asText.toString();
        return "ShoppingCart{" +
                "cart=" + res +
                '}';
    }
}
