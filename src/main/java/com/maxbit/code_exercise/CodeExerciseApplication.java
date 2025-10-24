package com.maxbit.code_exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

@SpringBootApplication
public class CodeExerciseApplication {

	public static void main(String[] args) {
		try {
			Dotenv dotenv = Dotenv.configure()
					.ignoreIfMissing()
					.ignoreIfMalformed()
					.load();

			dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		} catch (DotenvException e) {
			System.out.println("Skipping .env file loading: " + e.getMessage());
		}
		SpringApplication.run(CodeExerciseApplication.class, args);
	}

}
