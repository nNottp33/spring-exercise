package com.maxbit.code_exercise.features.book;

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

    public Books create(Books book) {
        return booksRepository.save(book);
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
}
