package com.example.mercatino.repository;

import com.example.mercatino.Model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryBooks extends JpaRepository<Books , Long> {
    Optional<Books> findByTitle(String title);

    Optional<Books> findByIsbn(String isbn);
}
