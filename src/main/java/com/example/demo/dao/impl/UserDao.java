package com.example.demo.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.IUserDao;
import com.example.demo.model.UserModel;
import com.example.demo.repository.IUserRepository;

@Component
public class UserDao extends GenericDao<UserModel, Long> implements IUserDao {

  @Autowired
  private IUserRepository userRepository;

  @Override
  public UserModel findByUsername(String username) {
    List<UserModel> lUserModel = userRepository.findByUsername(username);
    return !lUserModel.isEmpty() ? lUserModel.get(0) : null;
  }

}
