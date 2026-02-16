package com.example.to_do_list.repository;

import com.example.to_do_list.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserModel,String> {
    Optional<UserModel> findByEmail(String email);
}
