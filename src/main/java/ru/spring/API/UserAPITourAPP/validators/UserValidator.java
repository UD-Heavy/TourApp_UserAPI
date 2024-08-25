package ru.spring.API.UserAPITourAPP.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.spring.API.UserAPITourAPP.models.User;
import ru.spring.API.UserAPITourAPP.services.UserServiceImpl;

@Component
public class UserValidator implements Validator {

    private final UserServiceImpl userService;

    @Autowired
    public UserValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;


        if (userService.findByEmail(user.getEmail()).isPresent())
            errors.rejectValue("email", "409", "User with this email already exists");
    }
}
