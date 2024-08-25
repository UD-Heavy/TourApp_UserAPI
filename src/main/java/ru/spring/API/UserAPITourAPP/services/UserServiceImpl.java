package ru.spring.API.UserAPITourAPP.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.API.UserAPITourAPP.excexptions.IncorrectUserPasswordException;
import ru.spring.API.UserAPITourAPP.models.User;
import ru.spring.API.UserAPITourAPP.repositories.UserRepositories;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepositories userRepositories;

    @Autowired
    public UserServiceImpl(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    public User logIn(String password, String email) {
        Optional<User> userOptional = userRepositories.findUserByEmail(email);

        // Если пользователь найден
        if (userOptional.isPresent()) {
            // Получаем пользователя из Optional
            User user = userOptional.get();

            // Проверяем, совпадает ли введенный пароль с паролем, сохраненным в базе данных
            if (BCrypt.checkpw(password, user.getPassword())) {
                // Если пароли совпадают, возвращаем пользователя
                return user;
            }
        }
        throw new IncorrectUserPasswordException("Введён неверный пароль");
    }

    public Optional<User> findByEmail(String email) {
        return userRepositories.findUserByEmail(email);
    }

    @Transactional
    public void registerUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepositories.save(user);
    }
}
