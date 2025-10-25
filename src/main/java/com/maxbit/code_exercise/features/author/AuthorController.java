package com.maxbit.code_exercise.features.author;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxbit.code_exercise.features.author.models.Authors;
import com.maxbit.code_exercise.utils.ApiResponse;
import com.maxbit.code_exercise.utils.ResponseTransformer;

@RestController
@RequestMapping("author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Authors>>> getAuthors() {
        List<Authors> authors = authorService.getAuthors();
        return ResponseTransformer.success("Fetched author Success", authors);
    }

}
