package com.restaurant.chantee;

import com.restaurant.chantee.model.domain.entity.Product;
import com.restaurant.chantee.model.domain.entity.ShoppingCart;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    private static final ShoppingCart testCart = new ShoppingCart();
    private static final Map<Product, Integer> exampleCart = new HashMap<>();
    private static final List<Product> testProducts = new LinkedList<>();

    @BeforeClass
    public static void before() {
        for (int i = 0; i < 10; i++){
            Product product = new Product();
            product.setId(i);
            product.setTitle("product" + i);
            product.setPrice(100);
            testProducts.add(product);
        }
    }

    @Test
    public void addProduct() {
        for (Product p : testProducts){
            testCart.addProduct(p, p.getId());
            exampleCart.put(p, p.getId());
        }
        assertEquals(testCart.getCart(), exampleCart);
    }

    @Test
    public void changeQuantity() {
        for (Product p : testProducts){
            testCart.changeQuantity(p, p.getId() + 1);
            exampleCart.remove(p);
            exampleCart.put(p, p.getId() + 1);
        }
        assertEquals(testCart.getCart(), exampleCart);
    }

    @Test
    public void getTotalPrice() {
        int testCartPrice = testCart.getTotalPrice();
        int exampleCartPrice = 0;
        for (Product p : exampleCart.keySet()){
            exampleCartPrice += p.getPrice() * exampleCart.get(p);
        }
        assertEquals(testCartPrice, exampleCartPrice);
    }

    @Test
    public void generateProductList() {
        String productList1 = testCart.generateProductList();
        String productList2;
        StringBuilder sb = new StringBuilder();
        for (Product p : exampleCart.keySet()){
            String column = p.getTitle() + exampleCart.get(p) + "<br>";
            sb.append(column);
        }
        productList2 = sb.toString().trim();
        assertEquals(productList1, productList2);
    }

    @Test
    public void removeProduct() {
        Product product = new Product();
        product.setId(100);
        testCart.addProduct(product, 1);
        testCart.removeProduct(product);
        assertEquals(testCart.getCart(), exampleCart);
    }
}