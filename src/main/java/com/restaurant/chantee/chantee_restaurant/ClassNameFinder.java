package com.restaurant.chantee.chantee_restaurant;

public class ClassNameFinder {
    public static String getCurrentClass(){
        try {
            throw new RuntimeException();
        }catch (RuntimeException e) {
            return e.getStackTrace()[1].getClass().getName();
        }
    }
}
