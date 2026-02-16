package com.example.to_do_list.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoRequestDto {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private boolean completed;
}
