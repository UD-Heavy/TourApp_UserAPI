package ru.spring.API.UserAPITourAPP.excexptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
