package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;

@Repository
public interface IUserRepository extends IGenericRepository<UserModel, Long> {

    public List<UserModel> findByUsername(String username);

}
