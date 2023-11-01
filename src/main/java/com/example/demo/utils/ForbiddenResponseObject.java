package com.example.demo.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForbiddenResponseObject {

  private String timestamp;
  private Integer status;
  private String error;
  private String path;

}
