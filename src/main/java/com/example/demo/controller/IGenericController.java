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
import com.example.demo.utils.ForbiddenResponseObject;
import com.example.demo.utils.ResponseObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface IGenericController<T extends GenericModel, I extends Serializable> {

  @Operation(summary = "Get by id", description = "Get an object by specifying its id as a parameter. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class)))
  @ApiResponse(responseCode = "403", description = "Forbidden", content = {
      @Content(schema = @Schema(implementation = ForbiddenResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PreAuthorize("hasAnyAuthority('admin','user')")
  @GetMapping("/{id}")
  public ResponseEntity<ResponseObject> get(@PathVariable(required = true) I i);

  @Operation(summary = "Get list by id", description = "Get an object by specifying their ids as a list of parameters. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class)))
  @ApiResponse(responseCode = "403", description = "Forbidden", content = {
      @Content(schema = @Schema(implementation = ForbiddenResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PreAuthorize("hasAnyAuthority('admin','user')")
  @PostMapping("/get_multiple")
  public ResponseEntity<ResponseObject> get(@RequestBody(required = true) Collection<I> lI);

  @Operation(summary = "Create by object data", description = "Create an object by specifying its data in the request body. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class)))
  @ApiResponse(responseCode = "403", description = "Forbidden", content = {
      @Content(schema = @Schema(implementation = ForbiddenResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PreAuthorize("hasAuthority('admin')")
  @PostMapping
  public ResponseEntity<ResponseObject> create(@RequestBody(required = true) T t);

  @Operation(summary = "Create list by objects data", description = "Create a list of objects by specifying its data in the request body. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class)))
  @ApiResponse(responseCode = "403", description = "Forbidden", content = {
      @Content(schema = @Schema(implementation = ForbiddenResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PreAuthorize("hasAuthority('admin')")
  @PostMapping("/create_multiple")
  public ResponseEntity<ResponseObject> create(@RequestBody(required = true) Collection<T> lT);

  @Operation(summary = "Update by id and object data", description = "Update an object by specifying its id as a parameter and its data in the request body. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class)))
  @ApiResponse(responseCode = "403", description = "Forbidden", content = {
      @Content(schema = @Schema(implementation = ForbiddenResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PreAuthorize("hasAuthority('admin')")
  @PutMapping("/{id}")
  public ResponseEntity<ResponseObject> update(@PathVariable(required = true) I i, @RequestBody(required = true) T t);

  @Operation(summary = "Disable by id", description = "Disable a list of objects by specifying their ids in the request body. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class)))
  @ApiResponse(responseCode = "403", description = "Forbidden", content = {
      @Content(schema = @Schema(implementation = ForbiddenResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PreAuthorize("hasAuthority('admin')")
  @PatchMapping("/disable")
  public ResponseEntity<ResponseObject> disable(@RequestBody(required = true) Collection<I> lI);

  @Operation(summary = "Enable by id", description = "Enable a list of objects by specifying their ids in the request body. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class)))
  @ApiResponse(responseCode = "403", description = "Forbidden", content = {
      @Content(schema = @Schema(implementation = ForbiddenResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PreAuthorize("hasAuthority('admin')")
  @PatchMapping("/enable")
  public ResponseEntity<ResponseObject> enable(@RequestBody(required = true) Collection<I> lI);

  @Operation(summary = "Delete by id", description = "Delete an object by specifying its id as a parameter. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class)))
  @ApiResponse(responseCode = "403", description = "Forbidden", content = {
      @Content(schema = @Schema(implementation = ForbiddenResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PreAuthorize("hasAuthority('admin')")
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseObject> delete(@PathVariable(required = true) I i);

  @Operation(summary = "Delete list by id", description = "Delete a list of objects by specifying their ids in the request body. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class)))
  @ApiResponse(responseCode = "403", description = "Forbidden", content = {
      @Content(schema = @Schema(implementation = ForbiddenResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PreAuthorize("hasAuthority('admin')")
  @DeleteMapping
  public ResponseEntity<ResponseObject> delete(@RequestBody(required = true) Collection<I> lI);

}
