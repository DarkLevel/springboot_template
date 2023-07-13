package com.example.demo.controller;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.GenericModel;
import com.example.demo.utils.ResponseObject;

public interface IGenericController<T extends GenericModel, I extends Serializable> {

  @GetMapping("/{id}")
  public ResponseEntity<ResponseObject> get(@PathVariable(required = true) I i);

  @GetMapping
  public ResponseEntity<ResponseObject> get(@RequestParam(required = false) Collection<I> i);

  @PostMapping
  public ResponseEntity<ResponseObject> create(@RequestBody(required = true) T t);

  @PostMapping("/multiple")
  public ResponseEntity<ResponseObject> create(@RequestBody(required = true) Collection<T> lT);

  @PutMapping("/{id}")
  public ResponseEntity<ResponseObject> update(@PathVariable(required = true) I i, @RequestBody(required = true) T t);

  @PatchMapping("/disable")
  public ResponseEntity<ResponseObject> disable(@RequestBody(required = true) Collection<I> lI);

  @PatchMapping("/enable")
  public ResponseEntity<ResponseObject> enable(@RequestBody(required = true) Collection<I> lI);

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseObject> delete(@PathVariable(required = true) I i);

  @DeleteMapping
  public ResponseEntity<ResponseObject> delete(@RequestBody(required = true) Collection<I> lI);

}
