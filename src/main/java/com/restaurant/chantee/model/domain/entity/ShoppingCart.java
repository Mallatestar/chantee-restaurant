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

    /**
     * Calculating all cart positions price.
     * @return int Total price of shopping cart
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        Map<Product, Integer> cart = this.getCart();
        for (Product p : cart.keySet()){
            totalPrice += p.getPrice() * cart.get(p);
        }
        return totalPrice;
    }

    /**
     * Adding position in Shopping cart map
     * @param product entity from DB
     * @param quantity number from UI
     */
    public void addProduct(Product product, Integer quantity){
        if (product != null && quantity != null && quantity >= 0){
            if (this.cart.containsKey(product)){
                this.changeQuantity(product, quantity);
            }
            else {cart.put(product, quantity);}
        }
    }

    /**
     * Deleting position from cart
     * @param product entity from DB
     */
    public void removeProduct(Product product){
        if (product != null && cart.containsKey(product)){
            cart.remove(product);
        }
    }


    /**
     * Changing position quantity
     * @param product entity from DB
     * @param newQuantity number from UI
     */
    public void changeQuantity(Product product, Integer newQuantity){
        if (product != null && newQuantity != null && newQuantity >= 0){
            cart.remove(product);
            cart.put(product, newQuantity);
        }
    }

    /**
     * Generates a html text for cart
     * @return html of cart
     */
    public String generateProductList(){
        StringBuilder sb = new StringBuilder();
        for (Product p : this.cart.keySet()){
            String column = "<tr>\n" +
                    "<td>"+ p.getTitle() +"</td>\n" +
                    "<td>" + cart.get(p) + "</td>\n" +
                    "<td class=\"alignright\">" + p.getPrice() + " UAH</td>\n" +
                    "</tr>\n";
            sb.append(column);
        }
        return sb.toString();
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
