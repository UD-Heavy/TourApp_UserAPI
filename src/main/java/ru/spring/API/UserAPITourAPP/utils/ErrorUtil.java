package ru.spring.API.UserAPITourAPP.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.spring.API.UserAPITourAPP.excexptions.UserNotFoundException;

import java.util.List;

public class ErrorUtil {

    public static void returnErrorsToUser(BindingResult bindingResult){
        StringBuilder errorMessage = new StringBuilder();

        List<FieldError> errorList = bindingResult.getFieldErrors();

        for (FieldError error : errorList){
            errorMessage.append(error.getField())
                    .append(" — ").append(error.getDefaultMessage())
                    .append("; ");
        }

        throw new UserNotFoundException(errorMessage.toString());
    }
}
