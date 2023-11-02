package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenEndpoints {

  CONFIGURATION("/configuration"), SWAGGER("/swagger-ui"), DOCS("/docs"), AUTH("/auth");

  private final String value;

}
