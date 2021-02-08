package com.restaurant.chantee.model.Exception;

public class NoSuchEntityException extends Exception {

    public NoSuchEntityException(){
        super();
    }

    public NoSuchEntityException(String message){
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause){
        super(message, cause);
    }
}
