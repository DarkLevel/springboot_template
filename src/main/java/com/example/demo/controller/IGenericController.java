package com.example.demo.controller;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.GenericModel;
import com.example.demo.utils.ResponseObject;

public interface IGenericController<T extends GenericModel, I extends Serializable> {

  @PreAuthorize("hasAnyAuthority('admin','user')")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseObject> get(@PathVariable(required = true) I i);

  @PreAuthorize("hasAnyAuthority('admin','user')")
  @PostMapping("/get_multiple")
  public ResponseEntity<ResponseObject> get(@RequestBody(required = true) Collection<I> lI);

  @PreAuthorize("hasAuthority('admin')")
  @PostMapping
  public ResponseEntity<ResponseObject> create(@RequestBody(required = true) T t);

  @PreAuthorize("hasAuthority('admin')")
  @PostMapping("/create_multiple")
  public ResponseEntity<ResponseObject> create(@RequestBody(required = true) Collection<T> lT);

  @PreAuthorize("hasAuthority('admin')")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseObject> update(@PathVariable(required = true) I i, @RequestBody(required = true) T t);

  @PreAuthorize("hasAuthority('admin')")
  @PatchMapping("/disable")
  public ResponseEntity<ResponseObject> disable(@RequestBody(required = true) Collection<I> lI);

  @PreAuthorize("hasAuthority('admin')")
  @PatchMapping("/enable")
  public ResponseEntity<ResponseObject> enable(@RequestBody(required = true) Collection<I> lI);

  @PreAuthorize("hasAuthority('admin')")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseObject> delete(@PathVariable(required = true) I i);

  @PreAuthorize("hasAuthority('admin')")
  @DeleteMapping
  public ResponseEntity<ResponseObject> delete(@RequestBody(required = true) Collection<I> lI);

}
