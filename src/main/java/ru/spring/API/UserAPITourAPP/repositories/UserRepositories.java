package ru.spring.API.UserAPITourAPP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spring.API.UserAPITourAPP.models.User;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
}
