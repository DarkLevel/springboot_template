package com.example.demo.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetails implements UserDetails {

  private UserModel userModel;

  private List<UserRoleModel> lUserRoleModel;

  public CustomUserDetails(UserModel userModel, List<UserRoleModel> lUserRoleModel) {
    super();
    this.userModel = userModel;
    this.lUserRoleModel = lUserRoleModel;
  }

  @Override
  public String getUsername() {
    return userModel.getUsername();
  }

  @Override
  public String getPassword() {
    return userModel.getPassword();
  }

  @Override
  public boolean isEnabled() {
    return userModel.isEnabled();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<? extends GrantedAuthority> authorities = lUserRoleModel
        .stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleModel().getName().toUpperCase()))
        .collect(Collectors.toList());

    log.info("{\"" + userModel.getUsername() + "_roles\":["
        + authorities.stream().map(e -> "\"" + e.getAuthority() + "\"").collect(Collectors.joining(", ")) + "]}");

    return authorities;
  }

}
