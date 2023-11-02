package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.UserModel;
import com.example.demo.utils.ResponseObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "User management endpoints")
@RequestMapping("/user")
public interface IUserController extends IGenericController<UserModel, Long> {

  @Operation(summary = "Retrieve by username", description = "Get a list of users by specifying their usernames. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "403", description = "Forbidden", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PreAuthorize("hasAnyAuthority('admin')")
  @GetMapping("/username/{username}")
  public ResponseEntity<ResponseObject> get(@PathVariable(required = true) String username);

}
