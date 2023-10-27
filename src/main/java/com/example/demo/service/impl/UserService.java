package com.example.demo.service.impl;

import java.util.Collection;
import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserDao;
import com.example.demo.exception.GenericException;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;
import com.example.demo.service.IUserRoleService;
import com.example.demo.service.IUserService;

@Service
public class UserService extends GenericService<UserModel, Long> implements IUserService {

  @Autowired
  private IUserDao userDao;

  @Autowired
  private IUserRoleService userRoleService;

  @Override
  public UserModel getByUsername(String username) throws GenericException {
    try {
      return userDao.findByUsername(username);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 500);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public UserModel create(UserModel userModel) throws GenericException {
    try {
      UserModel createdUser = userDao.save(userModel);
      userRoleService.create(new UserRoleModel(createdUser, 2L));
      return createdUser;
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 500);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public Collection<UserModel> create(Collection<UserModel> lUserModel) throws GenericException {
    try {
      lUserModel.removeAll(Collections.singleton(null));
      Collection<UserModel> lCreatedUser = userDao.save(lUserModel);
      userRoleService.create(lCreatedUser.stream().map(e -> new UserRoleModel(e, 2L)).toList());
      return lCreatedUser;
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 500);
    }
  }

}
