package com.example.demo.service.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IGenericDao;
import com.example.demo.dao.IUserDao;
import com.example.demo.exception.GenericException;
import com.example.demo.filter.AuthUtilities;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;
import com.example.demo.service.IUserRoleService;
import com.example.demo.service.IUserService;

import jakarta.transaction.Transactional;

@Service
public class UserService extends GenericService<UserModel, Long> implements IUserService {

  private final IUserDao userDao;
  private final IUserRoleService userRoleService;

  public UserService(IGenericDao<UserModel, Long> genericDao, IUserDao userDao, IUserRoleService userRoleService) {
    super(genericDao);
    this.userDao = userDao;
    this.userRoleService = userRoleService;
  }

  @Override
  public UserModel getByUsername(String username) throws GenericException {
    UserModel userModel;

    try {
      userModel = userDao.findByUsername(username);

      if (!AuthUtilities.isAdmin() && userModel != null
          && !AuthUtilities.getLoggedUsername().equals(userModel.getUsername())) {
        throw new GenericException("Forbidden", 403);
      }
    } catch (GenericException e) {
      throw new GenericException(e.getMessage(), e, e.getCode());
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
    }

    return userModel;
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public UserModel create(@NonNull UserModel userModel) throws GenericException {
    try {
      UserModel createdUser = userDao.save(userModel);
      userRoleService.create(new UserRoleModel(createdUser, 2L));
      return createdUser;
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 400);
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
      throw new GenericException(e.getMessage(), e, 400);
    }
  }

  @Override
  public void afterGet(UserModel userModel) throws GenericException {
    if (!AuthUtilities.isAdmin() && userModel != null
        && !AuthUtilities.getLoggedUsername().equals(userModel.getUsername())) {
      throw new GenericException("Forbidden", 403);
    }
  }

}
