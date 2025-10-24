package com.maxbit.code_exercise.features.book;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.maxbit.code_exercise.features.author.models.Authors;
import com.maxbit.code_exercise.features.book.dtos.BooksDto;
import com.maxbit.code_exercise.features.book.dtos.UpdateBookDto;
import com.maxbit.code_exercise.features.book.models.Books;
import com.maxbit.code_exercise.utils.ApiResponse;
import com.maxbit.code_exercise.utils.ResponseTransformer;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public ResponseEntity<ApiResponse<List<Books>>> getBooks() {
        try {
            List<Books> books = booksRepository.findAll();
            return ResponseTransformer.success("Fetched books!", books);
        } catch (Exception e) {
            return ResponseTransformer.serverError(e.getMessage());
        }
    }

    public ResponseEntity<ApiResponse<Books>> getById(Long id) {
        return getBookById(id)
                .map(book -> ResponseTransformer.success("Fetched book!", book))
                .orElse(ResponseTransformer.notFound("Book not found"));
    }

    public ResponseEntity<ApiResponse<String>> create(BooksDto book) {
        try {
            Optional<Books> existBook = booksRepository.findByIsbn(book.getIsbn());
            if (existBook.isPresent()) {
                return ResponseTransformer.badRequest("Book already exists");
            }

            Books bookData = toEntity(book);
            booksRepository.save(bookData);
            return ResponseTransformer.created("Created books");
        } catch (Exception e) {
            return ResponseTransformer.serverError(e.getMessage());
        }
    }

    public ResponseEntity<ApiResponse<String>> update(UpdateBookDto updateInput) {
        Optional<Books> bookOptional = booksRepository.findById(updateInput.getBookId());

        if (!bookOptional.isPresent()) {
            return ResponseTransformer.notFound("Book not found");
        }

        try {
            Books book = bookOptional.get();

            if (updateInput.getTitle() != null) {
                book.setTitle(updateInput.getTitle());
            }

            if (updateInput.getDescription() != null) {
                book.setDescription(updateInput.getDescription());
            }

            if (updateInput.getPrice() != null) {
                book.setPrice(updateInput.getPrice());
            }

            if (updateInput.getReleaseDate() != null) {
                book.setReleaseDate(updateInput.getReleaseDate());
            }

            if (updateInput.getAuthorId() != null) {
                book.setAuthor(Authors.builder()
                        .id(updateInput.getAuthorId())
                        .build());
            }

            booksRepository.save(book);
            return ResponseTransformer.success("Book updated successfully");
        } catch (Exception e) {
            return ResponseTransformer.serverError(e.getMessage());
        }
    }

    public ResponseEntity<ApiResponse<String>> delete(Long id) {
        Optional<Books> bookOptional = booksRepository.findById(id);

        if (!bookOptional.isPresent()) {
            return ResponseTransformer.notFound("Book not found");
        }

        booksRepository.deleteById(id);
        return ResponseTransformer.success("Deleted! book");
    }

    private Optional<Books> getBookById(Long id) {
        return booksRepository.findById(id);
    }

    private static Books toEntity(BooksDto dto) {
        return Books.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .isbn(dto.getIsbn())
                .price(dto.getPrice())
                .releaseDate(dto.getReleaseDate())
                .author(Authors.builder()
                        .id(dto.getAuthorId())
                        .build())
                .build();
    }
}
