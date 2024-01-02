package com.example.demo.dao.impl;

import org.springframework.stereotype.Component;

import com.example.demo.dao.IRoleDao;
import com.example.demo.model.RoleModel;
import com.example.demo.repository.IGenericRepository;

@Component
public class RoleDao extends GenericDao<RoleModel, Long> implements IRoleDao {

  public RoleDao(IGenericRepository<RoleModel, Long> genericRepository) {
    super(genericRepository);
  }

}
