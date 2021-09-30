package com.example.mercatino.repository;

import com.example.mercatino.Model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryBooks extends JpaRepository<Books , Long> {

    Optional<List<Books>> findByTitle(String title);

    Optional<List<Books>> findByIsbn(String isbn);
}
