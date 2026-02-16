package com.example.to_do_list.repository;

import com.example.to_do_list.model.ToDoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDoModel,String> {
}
