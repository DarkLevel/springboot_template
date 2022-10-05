package com.example.demo.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.IRoleController;
import com.example.demo.model.RoleModel;
import com.example.demo.service.impl.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController extends GenericController<RoleModel, RoleService>
                implements IRoleController {

}
