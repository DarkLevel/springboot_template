package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.model.UserModel;
import com.example.demo.utils.ResponseObject;
import com.example.demo.utils.UnauthorizedResponseObject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "User management endpoints")
public interface IUserController extends IGenericController<UserModel, Long> {

  @Operation(summary = "Retrieve by username", description = "Get a list of users by specifying their usernames. The expected response object is located inside the data object.")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "401", content = { @Content(schema = @Schema(implementation = UnauthorizedResponseObject.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = ResponseObject.class), mediaType = "application/json") })
  })
  @GetMapping("/username/{username}")
  public ResponseEntity<ResponseObject> get(@PathVariable(required = true) String username);

}
