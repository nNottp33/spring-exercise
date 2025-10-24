package com.maxbit.code_exercise.features.book.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = UpdateBookDto.UpdateBookDtoBuilder.class)
public class UpdateBookDto {
    @NotNull(message = "Book ID is required")
    @Positive(message = "Book ID must be positive")
    @JsonProperty("book_id")
    private Long bookId;

    private String title;

    private String description;

    private Long price;

    @JsonProperty("release_date")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate releaseDate;

    @Positive(message = "Author ID must be positive")
    @JsonProperty("author_id")
    private Long authorId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UpdateBookDtoBuilder {
    }
}
