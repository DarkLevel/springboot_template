package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.RoleModel;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Role", description = "Role management endpoints")
@RequestMapping("/role")
public interface IRoleController extends IGenericController<RoleModel, Long> {

}
