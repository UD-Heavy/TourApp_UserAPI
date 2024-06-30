package ru.spring.API.UserAPITourAPP.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.spring.API.UserAPITourAPP.utils.PasswordUtils;

import java.security.NoSuchAlgorithmException;

@Component
public class Sha256PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            return PasswordUtils.hashSha256(rawPassword.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String hashedPassword = null;
        try {
            hashedPassword = PasswordUtils.hashSha256(rawPassword.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return encodedPassword.equals(hashedPassword);
    }
}