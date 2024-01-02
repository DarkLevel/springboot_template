package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.dao.IGenericDao;
import com.example.demo.model.UserRoleModel;
import com.example.demo.service.IUserRoleService;

@Service
public class UserRoleService extends GenericService<UserRoleModel, Long> implements IUserRoleService {

  public UserRoleService(IGenericDao<UserRoleModel, Long> genericDao) {
    super(genericDao);
  }

}
