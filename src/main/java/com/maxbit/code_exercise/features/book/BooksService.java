package com.maxbit.code_exercise.features.book;

import com.maxbit.code_exercise.features.author.models.Authors;
import com.maxbit.code_exercise.features.book.dtos.BooksDto;
import com.maxbit.code_exercise.features.book.models.Books;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Books> getBooks() {
        return booksRepository.findAll();

    }

    public Books getById(Long id) {
        return booksRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Books create(BooksDto book) {
        Books bookData = toEntity(book);
        System.out.println(bookData);
        return booksRepository.save(bookData);
    }

    public Books update(Long id, Books updateInput) {
        Books book = getById(id);
        book.setTitle(updateInput.getTitle());
        book.setAuthor(updateInput.getAuthor());
        return booksRepository.save(book);
    }

    public void delete(Long id) {
        booksRepository.deleteById(id);
    }

    private static Books toEntity(BooksDto dto) {
        System.out.println(dto.getTitle());
        System.out.println(dto.getIsbn());
        System.out.println(dto.getPrice());

        return Books.builder()
                .title(dto.getTitle())
                .isbn(dto.getIsbn())
                .price(dto.getPrice())
                .author(Authors.builder()
                        .id(Long.valueOf(dto.getAuthorId()))
                        .build())
                .build();
    }
}
