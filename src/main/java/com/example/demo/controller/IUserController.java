package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.UserModel;

public interface IUserController extends IGenericController<UserModel, Long> {

    @GetMapping("/username/{username}")
    public ResponseEntity<?> get(@PathVariable(required = true) String username);

}
