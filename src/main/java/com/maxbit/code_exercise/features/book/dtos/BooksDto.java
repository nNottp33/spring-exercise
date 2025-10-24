package com.maxbit.code_exercise.features.book.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BooksDto {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Long price;

    @NotNull(message = "Release Date is required")
    @JsonProperty("release_date")
    private LocalDate releaseDate;

    @NotNull(message = "Author ID is required")
    @Positive(message = "Author ID must be positive")
    @JsonProperty("author_id")
    private Long authorId;
}
