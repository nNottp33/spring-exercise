package com.maxbit.code_exercise.features.author;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maxbit.code_exercise.features.author.models.Authors;

@Service
public class AuthorService {

    private final AuthorsRepository authorsRepository;

    public AuthorService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public List<Authors> getAuthors() {
        return authorsRepository.findAll();

    }
}
