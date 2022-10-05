package com.example.demo.dao.impl;

import org.springframework.stereotype.Component;

import com.example.demo.dao.IRoleDao;
import com.example.demo.model.RoleModel;

@Component
public class RoleDao extends GenericDao<RoleModel, Long> implements IRoleDao {

}
