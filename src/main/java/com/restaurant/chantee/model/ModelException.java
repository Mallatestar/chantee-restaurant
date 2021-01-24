package com.restaurant.chantee.model;

public class ModelException extends Exception {

    public ModelException(){
        super();
    }

    public ModelException(String message){
        super(message);
    }

    public ModelException(String message, Throwable cause){
        super(message, cause);
    }
}
