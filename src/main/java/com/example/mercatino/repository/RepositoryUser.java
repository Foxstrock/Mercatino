package com.example.mercatino.repository;

import com.example.mercatino.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryUser extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    Optional<User> findBySurname(String surname);
}
