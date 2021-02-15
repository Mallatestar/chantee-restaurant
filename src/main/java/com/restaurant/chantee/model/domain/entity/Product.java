package com.restaurant.chantee.model.domain.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Product implements Serializable {
    private int id;
    private String title;
    private int price;
    private String description;
    private String img_path;
    private int category;


    public Product() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static class CompareByName implements Comparator<Product>{
        @Override
        public int compare(Product o1, Product o2) {
            return (o1.getTitle().compareTo(o2.getTitle()));
        }
    }

    public static class CompareByPrice implements Comparator<Product>{

        @Override
        public int compare(Product o1, Product o2) {
            return (o1.getPrice() - o2.getPrice());
        }
    }
}
