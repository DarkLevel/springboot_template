package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseObject {

  private Metadata metadata;
  private Object data;

  private ResponseObject(Metadata metadata, Object data) {
    this.data = data;
  }

  public static ResponseObject create(int status, Object data) {
    return new ResponseObject(
        new Metadata(status, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssXXX").format(new Date())), data);
  }

}
