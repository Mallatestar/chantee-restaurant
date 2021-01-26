package com.restaurant.chantee.controller;

public class ControllerException extends Exception {

    public ControllerException(){
        super();
    }

    public ControllerException(String message){
        super(message);
    }

    public ControllerException(String message, Throwable cause){
        super(message, cause);
    }
}
