package com.example.demo.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

  private UserModel userModel;

  private Collection<? extends GrantedAuthority> authorities;

  public CustomUserDetails(UserModel userModel, Collection<? extends GrantedAuthority> authorities) {
    super();
    this.userModel = userModel;
    this.authorities = authorities;
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
    return authorities;
  }

}
