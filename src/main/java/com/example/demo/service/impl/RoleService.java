package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.dao.IGenericDao;
import com.example.demo.model.RoleModel;
import com.example.demo.service.IRoleService;

@Service
public class RoleService extends GenericService<RoleModel, Long> implements IRoleService {

  public RoleService(IGenericDao<RoleModel, Long> genericDao) {
    super(genericDao);
  }

}
