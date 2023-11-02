package com.example.demo.utils;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseObject {

  private Metadata metadata;
  private Object data;

  public static ResponseObject create(int status, Object data) {
    return new ResponseObject(new Metadata(status, Utilities.formatDateToISO(new Date())), data);
  }

}
