package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.AuthModel;
import com.example.demo.utils.ResponseObject;

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
  @ApiResponse(responseCode = "404", description = "Not found", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PostMapping("/login")
  public ResponseEntity<ResponseObject> login(@RequestBody(required = true) AuthModel authModel);

  @Operation(summary = "Refresh token", description = "Refresh access token by specifying the refresh token. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "404", description = "Not found", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PostMapping("/refresh_token")
  public ResponseEntity<ResponseObject> refreshToken(@RequestBody(required = true) String refreshToken);

  @Operation(summary = "Revoke token", description = "Revoke refresh token. The expected response object is located inside the data object.")
  @ApiResponse(responseCode = "200", description = "Success", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "404", description = "Not found", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @ApiResponse(responseCode = "500", description = "Internal server error", content = {
      @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  @PostMapping("/revoke_token")
  public ResponseEntity<ResponseObject> revokeRefreshToken(@RequestBody(required = true) String refreshToken);

}
