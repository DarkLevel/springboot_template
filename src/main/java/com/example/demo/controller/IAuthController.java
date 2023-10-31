package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.AuthModel;
import com.example.demo.utils.ResponseObject;

@RequestMapping("/auth")
public interface IAuthController {

  @PostMapping("/login")
  public ResponseEntity<ResponseObject> login(@RequestBody(required = true) AuthModel authModel);

}
