package com.example.demo.dao;

import com.example.demo.model.UserModel;

public interface IUserDao extends IGenericDao<UserModel, Long> {

    public UserModel findByUsername(String username);

}
