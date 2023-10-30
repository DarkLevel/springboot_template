package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserDao;
import com.example.demo.dao.IUserRoleDao;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private IUserDao userDao;

  @Autowired
  private IUserRoleDao userRoleDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username == null || (username != null && username.trim().isEmpty())) {
      log.error("The user is empty");
      throw new UsernameNotFoundException("The user is empty");
    }

    UserModel userModel = userDao.findByUsername(username);

    if (userModel == null) {
      log.error("The user doesn't exists");
      throw new UsernameNotFoundException("The user doesn't exists");
    }

    List<UserRoleModel> lUserRoleModel = userRoleDao.findByUserModel(userModel);

    if (lUserRoleModel == null || (lUserRoleModel != null && lUserRoleModel.isEmpty())) {
      log.error("The user has no roles asigned");
      throw new UsernameNotFoundException("The user has no roles asigned");
    }

    return new CustomUserDetails(userModel, lUserRoleModel);
  }

}
