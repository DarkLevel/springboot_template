package com.example.demo.service;

import com.example.demo.exception.GenericException;
import com.example.demo.model.UserModel;

public interface IUserService extends IGenericService<UserModel, Long> {

  public UserModel getByUsername(String username) throws GenericException;

}
