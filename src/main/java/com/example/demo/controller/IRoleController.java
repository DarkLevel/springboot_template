package com.example.demo.controller;

import com.example.demo.model.RoleModel;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Role", description = "Role management endpoints")
public interface IRoleController extends IGenericController<RoleModel, Long> {

}
