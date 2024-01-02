package com.example.demo.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.IUserController;
import com.example.demo.exception.GenericException;
import com.example.demo.model.UserModel;
import com.example.demo.service.impl.UserService;
import com.example.demo.utils.ResponseObject;
import com.example.demo.utils.ResponseUtils;

@RestController
public class UserController extends GenericController<UserModel, UserService>
    implements IUserController {

  private final UserService userService;

  public UserController(UserService userService) {
    super(userService);
    this.userService = userService;
  }

  @Override
  public ResponseEntity<ResponseObject> get(String username) {
    ResponseEntity<ResponseObject> responseEntity;

    try {
      responseEntity = ResponseUtils.getResponseEntity(userService.getByUsername(username));
    } catch (GenericException e) {
      responseEntity = ResponseUtils.getResponseEntity(e);
    }

    return responseEntity;
  }

}
