package com.example.demo.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserRoleDao;
import com.example.demo.exception.GenericException;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;
import com.example.demo.service.IUserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private IUserService userService;

  @Autowired
  private IUserRoleDao userRoleDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.trim().isEmpty()) {
      throw new UsernameNotFoundException("The user is empty");
    }

    UserModel userModel;

    try {
      userModel = userService.getByUsername(username);
    } catch (GenericException e) {
      throw new UsernameNotFoundException("Error retrieving user");
    }

    if (userModel == null) {
      throw new UsernameNotFoundException("The user doesn't exists");
    }

    List<UserRoleModel> lUserRoleModel = userRoleDao.findByUserModel(userModel);

    if (lUserRoleModel.isEmpty()) {
      throw new UsernameNotFoundException("The user has no roles asigned");
    }

    Collection<? extends GrantedAuthority> authorities = lUserRoleModel
        .stream()
        .map(role -> new SimpleGrantedAuthority(role.getRoleModel().getName()))
        .collect(Collectors.toList());

    return new CustomUserDetails(userModel, authorities);
  }

}
