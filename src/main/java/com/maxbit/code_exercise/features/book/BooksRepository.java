package com.maxbit.code_exercise.features.book;

import com.maxbit.code_exercise.features.book.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {

}