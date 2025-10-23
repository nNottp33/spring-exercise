package com.maxbit.code_exercise.features.book;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxbit.code_exercise.features.book.dtos.BooksDto;
import com.maxbit.code_exercise.features.book.models.Books;
import com.maxbit.code_exercise.utils.ApiResponse;
import com.maxbit.code_exercise.utils.ResponseTransformer;

@RestController
@RequestMapping("book")
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Books>>> getBooks() {
        List<Books> books = booksService.getBooks();
        return ResponseTransformer.success(books, "Fetched books Success");
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<String>> createBook(@Valid @RequestBody BooksDto book) {
        try {
            System.out.print(book);
            booksService.create(book);
            return ResponseTransformer.created("Created books");
        } catch (Exception e) {
            return ResponseTransformer.serverError(e.getMessage());
        }
    }

}