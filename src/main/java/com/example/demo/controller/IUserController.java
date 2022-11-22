package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.UserModel;
import com.example.demo.utils.ResponseObject;

public interface IUserController extends IGenericController<UserModel, Long> {

    @GetMapping("/username/{username}")
    public ResponseEntity<ResponseObject> get(@PathVariable(required = true) String username);

}
