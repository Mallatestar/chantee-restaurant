package com.restaurant.chantee.view;

public class ViewException extends Exception {

    public ViewException(){
        super();
    }

    public ViewException(String message){
        super(message);
    }

    public ViewException(String message, Throwable cause){
        super(message, cause);
    }
}
