package com.example.demo.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IRoleDao;
import com.example.demo.dao.IUserDao;
import com.example.demo.dao.IUserRoleDao;
import com.example.demo.exception.GenericException;
import com.example.demo.model.RoleModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserRoleModel;
import com.example.demo.service.IUserRoleService;
import com.example.demo.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService extends GenericService<UserModel, Long> implements IUserService, UserDetailsService {

  @Autowired
  private IUserDao userDao;

  @Autowired
  private IUserRoleDao userRoleDao;

  @Autowired
  private IRoleDao roleDao;

  @Autowired
  private IUserRoleService userRoleService;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel userModel = userDao.findByUsername(username);

    if (userModel == null) {
      log.error("The user doesn't exists");
      throw new UsernameNotFoundException("The user doesn't exists");
    }

    List<UserRoleModel> lUserRoleModel = userRoleDao.findByUserModel(userModel);
    roleDao.get(lUserRoleModel.stream().map(e -> e.getRoleModel().getId()).toList());

    List<GrantedAuthority> authorities = lUserRoleModel
        .stream()
        .map(role -> new SimpleGrantedAuthority(role.getRoleModel().getName()))
        .collect(Collectors.toList());

    authorities.forEach(authority -> log.info("Role: ".concat(authority.getAuthority())));

    return new User(userModel.getUsername(), userModel.getPassword(), !userModel.isDisabled(), true, true, true,
        authorities);
  }

  @Override
  public UserModel getByUsername(String username) throws GenericException {
    try {
      return userDao.findByUsername(username);
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 500);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public UserModel create(UserModel userModel) throws GenericException {
    try {
      UserModel createdUser = userDao.save(userModel);
      userRoleService.create(new UserRoleModel(createdUser, new RoleModel(2L)));
      return createdUser;
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 500);
    }
  }

  @Override
  @Transactional(rollbackOn = GenericException.class)
  public Collection<UserModel> create(Collection<UserModel> lUserModel) throws GenericException {
    try {
      lUserModel.removeAll(Collections.singleton(null));
      Collection<UserModel> lCreatedUser = userDao.save(lUserModel);
      userRoleService.create(lCreatedUser.stream().map(e -> new UserRoleModel(e, new RoleModel(2L))).toList());
      return lCreatedUser;
    } catch (Exception e) {
      throw new GenericException(e.getMessage(), e, 500);
    }
  }

}
