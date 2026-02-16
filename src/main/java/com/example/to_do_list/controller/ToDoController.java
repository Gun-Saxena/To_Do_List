package com.example.to_do_list.controller;

import com.example.to_do_list.dto.ToDoRequestDto;
import com.example.to_do_list.dto.ToDoResponseDto;
import com.example.to_do_list.service.ToDoService;
import com.example.to_do_list.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ToDoController {
    private final ToDoService service;
    private final JwtUtil jwtUtil;

    public ToDoController(ToDoService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    private void checkToken(String authHeader){
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Invalid token");
        }
        String token=authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }

    //Create todo
    @PostMapping
    public ToDoResponseDto createTodo(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody ToDoRequestDto todo
    ){
        checkToken(authHeader);
        return service.addTodo(todo);
    }

    // GET ALL TODOS
    @GetMapping
    public List<ToDoResponseDto> getTodos(
            @RequestHeader("Authorization") String authHeader
    ){
        checkToken(authHeader);
        return service.getAllTodos();
    }

    // UPDATE TODO
    @PutMapping("/{id}")
    public ToDoResponseDto updateTodo(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id,
            @RequestBody ToDoRequestDto todo
    ){
        checkToken(authHeader);
        return service.updateTodo(id, todo);
    }

    // DELETE TODO
    @DeleteMapping("/{id}")
    public ToDoResponseDto deleteTodo(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String id
    ){
        checkToken(authHeader);
        return service.deleteTodo(id);
    }
}
