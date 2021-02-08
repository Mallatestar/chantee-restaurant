package com.restaurant.chantee.model.Exception;

public class LoginException extends Exception {

    public LoginException(){
        super();
    }

    public LoginException(String message){
        super(message);
    }

    public LoginException(String message, Throwable cause){
        super(message, cause);
    }
}
