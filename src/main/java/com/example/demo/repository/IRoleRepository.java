package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.model.RoleModel;

@Repository
public interface IRoleRepository extends IGenericRepository<RoleModel, Long> {

}
