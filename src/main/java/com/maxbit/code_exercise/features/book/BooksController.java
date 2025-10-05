package com.maxbit.code_exercise.features.book;

import com.maxbit.code_exercise.features.book.models.Books;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("book")
public class BooksController {
    private final BooksService booksService;


    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }


    @GetMapping()
    public ResponseEntity<Map<String, List<Books>>> getBooks() {
        List<Books> books = booksService.getBooks();
        return ResponseEntity.ok()
                .body(Map.of("data", books));
    }

    @PostMapping()
    public Books createBook(Books book) {
        return booksService.create(book);
    }

}