package com.example.demo.model;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthModel {

  @Size(max = 20)
  private String username;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;

  @JsonProperty(access = Access.READ_ONLY)
  private Collection<String> roles;

  @JsonProperty(access = Access.READ_ONLY)
  private String access_token;

  @JsonProperty(access = Access.READ_ONLY)
  private String issuedAt;

  @JsonProperty(access = Access.READ_ONLY)
  private String expiration;

}
