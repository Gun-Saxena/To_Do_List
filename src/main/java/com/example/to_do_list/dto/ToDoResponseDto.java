package com.example.to_do_list.dto;

public record ToDoResponseDto(
        String id,
        String title,
        boolean completed
) {
}
