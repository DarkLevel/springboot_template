package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenModel {

  @JsonProperty(access = Access.READ_ONLY)
  private String token;

  @JsonProperty(access = Access.READ_ONLY)
  private String issuedAt;

  @JsonProperty(access = Access.READ_ONLY)
  private String expiration;

}
