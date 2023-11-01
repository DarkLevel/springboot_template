package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.AuthModel;
import com.example.demo.utils.ResponseObject;
import com.example.demo.utils.UnauthorizedResponseObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "Authorization management endpoints")
@RequestMapping("/auth")
public interface IAuthController {

  @Operation(summary = "Login", description = "Login by specifying username and password. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
      @Content(schema = @Schema(implementation = UnauthorizedResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PostMapping("/login")
  public ResponseEntity<ResponseObject> login(@RequestBody(required = true) AuthModel authModel);

}
