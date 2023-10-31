package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.RoleModel;

@RequestMapping("/role")
public interface IRoleController extends IGenericController<RoleModel, Long> {

}
