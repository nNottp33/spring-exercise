package com.maxbit.code_exercise.features.book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxbit.code_exercise.features.book.models.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {
    Optional<Books> findByIsbn(String isbn);
}