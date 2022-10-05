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

public interface IGenericController<T extends GenericModel, ID extends Serializable> {

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable(required = true) ID id);

	@GetMapping
	public ResponseEntity<?> get(@RequestParam(required = false) Collection<ID> id);

	@PostMapping
	public ResponseEntity<?> create(@RequestBody(required = true) T t);

	@PostMapping("/multiple")
	public ResponseEntity<?> create(@RequestBody(required = true) Collection<T> lT);

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable(required = true) ID id, @RequestBody(required = true) T t);

	@PatchMapping("/disable")
	public ResponseEntity<?> disable(@RequestBody(required = true) Collection<ID> lId);

	@PatchMapping("/enable")
	public ResponseEntity<?> enable(@RequestBody(required = true) Collection<ID> lId);

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(required = true) ID id);

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody(required = true) Collection<ID> lId);

}
