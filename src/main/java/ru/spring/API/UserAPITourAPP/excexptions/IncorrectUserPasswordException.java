package ru.spring.API.UserAPITourAPP.excexptions;

public class IncorrectUserPasswordException extends RuntimeException {
    public IncorrectUserPasswordException(String msg) {
        super(msg);
    }
}
