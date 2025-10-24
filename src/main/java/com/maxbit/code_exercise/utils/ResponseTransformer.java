package com.maxbit.code_exercise.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseTransformer {

    public static <T> ResponseEntity<ApiResponse<T>> success(String message) {
        ApiResponse<T> response = new ApiResponse<>(200, "Success", null);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(200, message, data);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message) {
        ApiResponse<T> response = new ApiResponse<>(201, message, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(201, message, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        ApiResponse<T> response = new ApiResponse<>(400, message, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> unauthorized(String message) {
        ApiResponse<T> response = new ApiResponse<>(401, message, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> forbidden(String message) {
        ApiResponse<T> response = new ApiResponse<>(403, message, null);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
        ApiResponse<T> response = new ApiResponse<>(404, message, null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> serverError(String message) {
        ApiResponse<T> response = new ApiResponse<>(500, message, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(int status, String message) {
        ApiResponse<T> response = new ApiResponse<>(status, message, null);
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> accepted(T data) {
        ApiResponse<T> response = new ApiResponse<>(202, "Request accepted", data);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> noContent() {
        return ResponseEntity.noContent().build();
    }
}