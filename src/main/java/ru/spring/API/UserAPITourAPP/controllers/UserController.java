package ru.spring.API.UserAPITourAPP.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.API.UserAPITourAPP.DTO.UserAnswerDTO;
import ru.spring.API.UserAPITourAPP.DTO.UserDTO;
import ru.spring.API.UserAPITourAPP.DTO.UserLogInDTO;
import ru.spring.API.UserAPITourAPP.excexptions.IncorrectUserPasswordException;
import ru.spring.API.UserAPITourAPP.excexptions.UserNotFoundException;
import ru.spring.API.UserAPITourAPP.models.User;
import ru.spring.API.UserAPITourAPP.services.UserServiceImpl;
import ru.spring.API.UserAPITourAPP.utils.RegisterPesponse;
import ru.spring.API.UserAPITourAPP.validators.UserLogInValidator;
import ru.spring.API.UserAPITourAPP.validators.UserValidator;

import static ru.spring.API.UserAPITourAPP.utils.ErrorUtil.returnErrorsToUser;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserValidator userValidator;
    private final UserLogInValidator userLogInValidator;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserValidator userValidator, UserLogInValidator userLogInValidator, UserServiceImpl userService, ModelMapper modelMapper) {
        this.userValidator = userValidator;
        this.userLogInValidator = userLogInValidator;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public ResponseEntity<UserAnswerDTO> login(@RequestBody @Valid UserLogInDTO userLogInDTO, BindingResult bindingResult) {

        User user = convertUserLogInDTOtoUser(userLogInDTO);

        userLogInValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            returnErrorsToUser(bindingResult);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ConvertUserToUserAnswerDTO(userService.logIn(user.getPassword(), user.getEmail())));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterPesponse> signUp(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {

        User user = convertUserDTOtoUser(userDTO);

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            returnErrorsToUser(bindingResult);

        userService.registerUser(user);

        return ResponseEntity.ok(new RegisterPesponse("ok", System.currentTimeMillis()));
    }


    @ExceptionHandler
    private ResponseEntity<RegisterPesponse> handleExceptions(UserNotFoundException e) {
        RegisterPesponse response = new RegisterPesponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<RegisterPesponse> handleExceptions(IncorrectUserPasswordException e) {
        RegisterPesponse response = new RegisterPesponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private User convertUserDTOtoUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private User convertUserLogInDTOtoUser(UserLogInDTO userLogInDTO) {
        return modelMapper.map(userLogInDTO, User.class);
    }

    private UserAnswerDTO ConvertUserToUserAnswerDTO(User user) {
        return modelMapper.map(user, UserAnswerDTO.class);
    }
}
