package com.example.demo.exception;

public class GenericException extends Exception {

  private final int code;

  public GenericException(int code) {
    super();
    this.code = code;
  }

  public GenericException(String message, int code) {
    super(message);
    this.code = code;
  }

  public GenericException(String message, Throwable cause, int code) {
    super(message, cause);
    this.code = code;
  }

  public GenericException(Throwable cause, int code) {
    super(cause);
    this.code = code;
  }

  public int getCode() {
    return this.code;
  }

}
