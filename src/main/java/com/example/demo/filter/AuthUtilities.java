package com.example.demo.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtilities {

  private AuthUtilities() {

  }

  public static boolean isAdmin() {
    boolean isAdmin = false;

    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      isAdmin = authentication.getAuthorities().stream().anyMatch(e -> "admin".equals(e.getAuthority()));
    } catch (Exception e) {
      isAdmin = false;
    }

    return isAdmin;
  }

  public static String getLoggedUsername() {
    String loggedUsername;

    try {
      loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
    } catch (Exception e) {
      loggedUsername = null;
    }

    return loggedUsername;
  }

}
