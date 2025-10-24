package com.maxbit.code_exercise.features.book.models;

import java.time.Instant;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maxbit.code_exercise.features.author.models.Authors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books", indexes = {
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_price", columnList = "price"),
        @Index(name = "idx_release_date", columnList = "release_date"),
        @Index(name = "idx_author_id", columnList = "author_id"),
// @Index(name = "idx_publisher_id", columnList = "publisher_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255, columnDefinition = "VARCHAR(255)")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(length = 20, unique = true, columnDefinition = "VARCHAR(20)")
    private String isbn;

    @Column(nullable = false, columnDefinition = "BIGINT")
    private Long price;

    @Column(name = "release_date", nullable = false, columnDefinition = "DATE")
    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "fk_book_author"))
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "books" })
    private Authors author;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "publisher_id", foreignKey = @ForeignKey(name =
    // "fk_book_publisher"))
    // @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "books" })
    // private Publisher publisher;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

}
