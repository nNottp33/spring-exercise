package com.maxbit.code_exercise.features.author;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxbit.code_exercise.features.author.models.Authors;

public interface AuthorsRepository extends JpaRepository<Authors, Long> {

}