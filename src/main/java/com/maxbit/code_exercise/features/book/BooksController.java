package com.maxbit.code_exercise.features.book;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxbit.code_exercise.features.book.dtos.BooksDto;
import com.maxbit.code_exercise.features.book.dtos.UpdateBookDto;
import com.maxbit.code_exercise.features.book.models.Books;
import com.maxbit.code_exercise.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("book")
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Books>>> getBooks() {
        return booksService.getBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Books>> getBook(@PathVariable Long id) {
        return booksService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<String>> createBook(@Valid @RequestBody BooksDto book) {
        return booksService.create(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBook(@PathVariable Long id) {
        return booksService.delete(id);
    }

    @PutMapping()
    public ResponseEntity<ApiResponse<String>> updateBook(@Valid @RequestBody UpdateBookDto book) {
        return booksService.update(book);
    }

}