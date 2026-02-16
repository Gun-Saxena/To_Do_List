package com.example.to_do_list.service;

import com.example.to_do_list.dto.ToDoRequestDto;
import com.example.to_do_list.dto.ToDoResponseDto;
import com.example.to_do_list.model.ToDoModel;
import com.example.to_do_list.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }
    // CREATE TODO
    public ToDoResponseDto addTodo(ToDoRequestDto dto) {
        ToDoModel todo = new ToDoModel();
        todo.setTitle(dto.getTitle());
        todo.setCompleted(dto.isCompleted());

        ToDoModel saved = repository.save(todo);

        return new ToDoResponseDto(
                saved.getId(),
                saved.getTitle(),
                saved.isCompleted()
        );
    }

    // GET ALL TODOS
    public List<ToDoResponseDto> getAllTodos() {
        return repository.findAll()
                .stream()
                .map(t -> new ToDoResponseDto(
                        t.getId(),
                        t.getTitle(),
                        t.isCompleted()
                ))
                .toList();
    }

    // UPDATE TODO
    public ToDoResponseDto updateTodo(String id, ToDoRequestDto dto) {
        ToDoModel existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        existing.setTitle(dto.getTitle());
        existing.setCompleted(dto.isCompleted());

        ToDoModel updated = repository.save(existing);

        return new ToDoResponseDto(
                updated.getId(),
                updated.getTitle(),
                updated.isCompleted()
        );
    }

    // DELETE TODO
    public ToDoResponseDto deleteTodo(String id) {
        ToDoModel existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        ToDoResponseDto response = new ToDoResponseDto(
                existing.getId(),
                existing.getTitle(),
                existing.isCompleted()
        );

        repository.delete(existing);
        return response;
    }
}
