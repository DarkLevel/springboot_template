package com.example.demo.dao.impl;

import org.springframework.stereotype.Component;

import com.example.demo.dao.IRefreshTokenDao;
import com.example.demo.model.RefreshTokenModel;

@Component
public class RefreshTokenDao extends GenericDao<RefreshTokenModel, Long> implements IRefreshTokenDao {

}
