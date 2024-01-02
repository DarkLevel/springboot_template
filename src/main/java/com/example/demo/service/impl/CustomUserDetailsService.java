package com.example.demo.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IUserDao;
import com.example.demo.dao.IUserRoleDao;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final IUserDao userDao;
  private final IUserRoleDao userRoleDao;

  public CustomUserDetailsService(IUserDao userDao, IUserRoleDao userRoleDao) {
    this.userDao = userDao;
    this.userRoleDao = userRoleDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.trim().isEmpty()) {
      throw new UsernameNotFoundException("The user is empty");
    }

    UserModel userModel = userDao.findByUsername(username);

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
