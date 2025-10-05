package com.maxbit.code_exercise.features.book.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BooksDto {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Title is required")
    private String isbn;


    @NotBlank(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotBlank(message = "AuthorId is required")
    private String authorId;


}
