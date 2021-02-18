package com.restaurant.chantee.model.Exception;

/**
 * Custom exception to signal that user data is invalid
 */
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
