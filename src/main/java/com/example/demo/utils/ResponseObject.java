package com.example.demo.utils;

import java.text.SimpleDateFormat;
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
    Metadata metadata = new Metadata(status, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssXXX").format(new Date()));
    return new ResponseObject(metadata, data);
  }

}
