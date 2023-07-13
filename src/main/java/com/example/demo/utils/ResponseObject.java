package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseObject {

    private String timestamp;
    private int status;
    private Object data;

    private ResponseObject(int status, Object data) {
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssXXX").format(new Date());
        this.status = status;
        this.data = data;
    }

    public static ResponseObject create(int status, Object data) {
        return new ResponseObject(status, data);
    }

}
