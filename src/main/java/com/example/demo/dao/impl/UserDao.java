package com.example.demo.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.dao.IUserDao;
import com.example.demo.model.UserModel;
import com.example.demo.repository.IUserRepository;

@Component
public class UserDao extends GenericDao<UserModel, Long> implements IUserDao {

  private final IUserRepository userRepository;

  public UserDao(IUserRepository userRepository) {
    super(userRepository);
    this.userRepository = userRepository;
  }

  @Override
  public UserModel findByUsername(String username) {
    Optional<UserModel> lUserModel = userRepository.findByUsername(username);
    return lUserModel.isPresent() ? lUserModel.get() : null;
  }

}
