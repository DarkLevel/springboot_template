package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.UserModel;
import com.example.demo.utils.ResponseObject;

@RequestMapping("/user")
public interface IUserController extends IGenericController<UserModel, Long> {

  @PreAuthorize("hasAnyAuthority('admin','user')")
  @GetMapping("/username/{username}")
  public ResponseEntity<ResponseObject> get(@PathVariable(required = true) String username);

}
