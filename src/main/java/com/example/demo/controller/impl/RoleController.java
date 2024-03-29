package com.example.demo.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.IRoleController;
import com.example.demo.model.RoleModel;
import com.example.demo.service.impl.RoleService;

@RestController
public class RoleController extends GenericController<RoleModel, RoleService> implements IRoleController {

  public RoleController(RoleService roleService) {
    super(roleService);
  }

}
